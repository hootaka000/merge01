package jp.co.cyberagent.infosys.ams.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.CustomerDao;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.utils.LoadError;
import jp.co.cyberagent.infosys.ams.utils.Paging;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * {@link CustomerService} の実装クラス.
 * @author shimizu.kenji
 *
 */
@Service("customerService")
public final class CustomerServiceImpl implements CustomerService {

    /** Customer DAO. */
    @Autowired
    private CustomerDao customerDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomers() throws AMSException {
        return customerDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Paging<Customer> getCustomers(final String filter, final int pageNumber) throws AMSException {
        int totalSize = customerDao.getCount();
        Paging<Customer> paging = new Paging<>(pageNumber, totalSize);
        int offset = (pageNumber - 1) * paging.getNumberInPage();
        offset = (offset < 0) ? 1 : offset;
        String customerfilter = (filter == null) ? "" : filter;
        List<Customer> customers =
                customerDao.find(customerfilter, offset, paging.getNumberInPage());
        for (Customer customer : customers) {
            paging.addItem(customer);
        }
        return paging;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer getCustomer(final long id) throws AMSException {
        return customerDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addCustomer(final Customer customer) throws AMSException {
        // TODO テスト
        String caonId = customer.getCaonId();
        if (caonId != null) {
            Customer company = customerDao.findByCaonId(caonId);
            if (company != null && !company.getName().equals(customer.getName())) {
                throw new AMSException("invalid company name.");
            }
        }

        saveCompany(caonId, customer.getName());
        if (customer.getDevision() != null) {
            saveDivision(caonId, customer.getName(), customer.getDevision());
            if (customer.getService() != null) {
                saveService(caonId, customer.getName(), customer.getDevision(), customer.getService());
            }
        }
    }
    /**
     * CAON IDを更新する.
     * @param id 顧客ID
     * @param caonId CAON ID
     * @throws AMSException CAON IDの更新に失敗した場合
     */
    @Override
    @Transactional
    public void updateCaonId(final long id, final String caonId) throws AMSException {
        Customer company = customerDao.findById(id);
        List<Customer> customers = customerDao.findByCompanyNameAll(company.getName());
        for (Customer customer : customers) {
            customer.setCaonId(caonId);
            customerDao.update(customer);
        }
    }
    /**
     * 企業情報を登録する.
     * @param caonId CAON ID
     * @param name 企業名
     * @throws AMSException 企業情報の登録に失敗した場合
     */
    private void saveCompany(final String caonId, final String name) throws AMSException {
        if (customerDao.findByCompanyName(name) != null) {
            return;
        }
        Customer company = new Customer();
        company.setCaonId(caonId);
        company.setName(name);
        company.setCustomerId(createCustomerId(caonId, null, null));
        customerDao.save(company);
    }

    /**
     * 事業部情報を登録する.
     * @param caonId CAON ID
     * @param companyName 企業名
     * @param divisionName 事業部名
     * @throws AMSException 事業部情報の登録に失敗した場合
     */
    private void saveDivision(final String caonId, final String companyName, final String divisionName)
            throws AMSException {
        if (customerDao.findByDivisionName(companyName, divisionName) != null) {
            return;
        }
        Customer company = customerDao.findByCompanyName(companyName);
        Customer division = new Customer();
        division.setCaonId(caonId);
        division.setName(companyName);
        division.setDevision(divisionName);
        division.setDevisionId(customerDao.getNextDivisionId(company));
        division.setParent(company);
        division.setCustomerId(createCustomerId(caonId, division.getDevisionId(), null));
        customerDao.save(division);
    }

    /**
     * サービス情報を登録する.
     * @param caonId CAON ID
     * @param companyName 企業名
     * @param divisionName 事業部名
     * @param serviceName サービス名
     * @throws AMSException サービス情報の登録に失敗した場合
     */
    private void saveService(final String caonId, final String companyName, final String divisionName,
            final String serviceName) throws AMSException {
        if (customerDao.findByServiceName(companyName, divisionName, serviceName) != null) {
            return;
        }
        Customer division = customerDao.findByDivisionName(companyName, divisionName);
        Customer service = new Customer();
        service.setCaonId(caonId);
        service.setName(companyName);
        service.setDevision(division.getDevision());
        service.setDevisionId(division.getDevisionId());
        service.setService(serviceName);
        service.setServiceId(customerDao.getNextServiceId(division));
        service.setParent(division);
        service.setCustomerId(createCustomerId(caonId, service.getDevisionId(), service.getServiceId()));
        customerDao.save(service);
    }

    /**
     * 顧客IDを生成する.
     * @param caonId CAON ID
     * @param divisionId 事業部ID
     * @param serviceId サービスID
     * @return 顧客ID
     */
    private String createCustomerId(final String caonId, final String divisionId,
            final String serviceId) {
        // TODO テスト
        if (divisionId == null) {
            return caonId + "-000-000";
        }
        if (serviceId == null) {
            return caonId + "-" + divisionId + "-000";
        }
        return caonId + "-" + divisionId + "-" + serviceId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer getCustomer(final String caonId) throws AMSException {
        return customerDao.findByCaonId(caonId);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Customer getCustomer(final String companyName, final String divisionName,
            final String serviceName) throws AMSException {
        // TODO テスト
        if (companyName == null) {
            throw new AMSException("company name is null.");
        }
        if (divisionName == null) {
            if (serviceName != null) {
                throw new AMSException("division name is null and service name is not null.");
            }
            return customerDao.findByCompanyName(companyName);
        }
        if (serviceName == null) {
            return customerDao.findByDivisionName(companyName, divisionName);
        }
        return customerDao.findByServiceName(companyName, divisionName, serviceName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeCustomer(final long id) throws AMSException {
        customerDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<LoadError<Integer, String>> loadCsv(final InputStream input,
            final ItemLoadOperator<Customer> operator) throws AMSException {
        // TODO テスト
        List<LoadError<Integer, String>> errors = new LinkedList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(input, "SJIS"))) {
            int index = 1;
            String[] lines;
            while ((lines = reader.readNext()) != null) {
                if (lines.length != 7) {
                    errors.add(new LoadError<>(index, "列数が正しくありません"));
                }
                if (lines[0] == null || lines[0].isEmpty()) {
                    Customer customer = new Customer();
                    customer.setCaonId(lines[1]);
                    customer.setName(lines[2]);
                    customer.setDevisionId(lines[3]);
                    customer.setDevision(lines[4]);
                    customer.setServiceId(lines[5]);
                    customer.setService(lines[6]);
                    operator.execute(customer);
                }
            }
        } catch (IOException e) {
            errors.add(new LoadError<>(0, "ファイルの読み込みに失敗しました. " + e.getMessage()));
        }
        return errors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Long, InputStream> createCsv(final List<Customer> customers) throws AMSException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(output, "SJIS"))) {

            for (Customer customer : customers) {
                String[] lines = new String[7];
                lines[1] = customer.getCaonId();
                lines[2] = customer.getName();
                lines[3] = customer.getDevisionId();
                lines[4] = customer.getDevision();
                lines[5] = customer.getServiceId();
                lines[6] = customer.getService();
                if (lines[1] != null) {
                    lines[0] = String.format("%s%s%s", lines[1],
                            (lines[3] != null) ? "-" + lines[3] : "",
                            (lines[5] != null) ? "-" + lines[5] : ""
                            );
                }
                writer.writeNext(lines);
                writer.flush();
            }

            final Long length = (long) output.toByteArray().length * Byte.SIZE;
            final InputStream input = new ByteArrayInputStream(output.toByteArray());

            return new HashMap<Long, InputStream>() {
                {
                    put(length, input);
                }
            };

        } catch (IOException e) {
           throw new AMSException(e.getMessage(), e);
        }
    }

    /**
     * {@link CustomerDao} を設定する.
     * @param customerDao {@link CustomerDao}
     */
    public void setCustomerDao(final CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
