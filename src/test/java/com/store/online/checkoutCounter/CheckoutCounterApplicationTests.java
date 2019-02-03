package com.store.online.checkoutCounter;

import com.store.online.Service.BillModel;
import com.store.online.Service.OrderStatus;
import com.store.online.dao.*;
import com.store.online.domain.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckoutCounterApplicationTests {

    @Autowired
    private static IHibernateDao hibernateDao;

    @Autowired
    private static CategoryDao categoryDao;
    @Autowired
    private static CustomerDao customerDao;
    @Autowired
    private static ProductDao productDao;
    @Autowired
    private static OrderDao orderDao;

    private static AnnotationConfigApplicationContext applicationContext;

    private String fileName = "E://Shital//bill.txt";

    @BeforeClass
    public static void init() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.store.online");
        applicationContext.refresh();
        hibernateDao = (IHibernateDao) applicationContext.getBean("hibernateDao");
        categoryDao = (CategoryDao) applicationContext.getBean("categoryDao");
        customerDao = (CustomerDao) applicationContext.getBean("customerDao");
        productDao = (ProductDao) applicationContext.getBean("productDao");
        orderDao = (OrderDao) applicationContext.getBean("orderDao");
    }

    /*@Test
    public void checkDB()
    {

    }*/


    @Test
    public void createCustomer() {
        Customer customer = new Customer();
        customer.setEmail("ashok@gmail.com");
        customer.setName("Ashok Patni");
        Set<Address> addressColl = new HashSet<>();
        Address address1 = new Address();
        address1.setHomeAddress("Baner Highway");
        address1.setCity("Pune");
        address1.setPincode(411087);
        address1.setPhonenumber("9876543212");
        addressColl.add(address1);

        Address address2 = new Address();
        address2.setHomeAddress("Aundh");
        address2.setCity("Pune");
        address2.setPincode(411057);
        address2.setPhonenumber("2345614234");
        addressColl.add(address2);

        customer.setAddressColl(addressColl);
        hibernateDao.save(customer);

        customer = new Customer();
        customer.setEmail("mahesh@yahoo.com");
        customer.setName("Mahesh Sharma");
        addressColl = new HashSet<>();
        address1 = new Address();
        address1.setHomeAddress("Baner Highway");
        address1.setCity("Pune");
        address1.setPincode(411087);
        address1.setPhonenumber("9876543212");
        addressColl.add(address1);


        address2 = new Address();
        address2.setHomeAddress("Aundh");
        address2.setCity("Pune");
        address2.setPincode(411057);
        address2.setPhonenumber("2345614234");
        addressColl.add(address2);

        customer.setAddressColl(addressColl);
        hibernateDao.save(customer);

        //if no exception then Creation is successful.
        Assert.assertEquals("Customers are created!! ", true, true);

    }

    @Test
    public void createCategory() {
        Category category = new Category();
        category.setDescription("Level 1 products");
        category.setName("A");
        Tax tax = new Tax();
        tax.setTaxPercent(10.0);
        tax.setCategory(category);
        category.setTax(tax);
        hibernateDao.save(category);

        category = new Category();
        category.setDescription("Level 2 products");
        category.setName("B");
        tax = new Tax();
        tax.setTaxPercent(20.0);
        tax.setCategory(category);
        category.setTax(tax);
        hibernateDao.save(category);

        category = new Category();
        category.setDescription("Level 3 products");
        category.setName("C");
        category.setTax(null);

        hibernateDao.save(category);

        //if no exception then Creation is successful.
        Assert.assertEquals("Categories are created!! ", true, true);

    }

    @Test
    public void createProduct() {
        Product product = new Product();
        product.setActive(true);
        product.setName("Soap");
        product.setPrice(Double.valueOf(50.75));
        product.setQuantity(Long.valueOf(1000));
        Category category = categoryDao.getCategoryByName("C");
        product.setCategory(category);
        hibernateDao.save(product);


        product = new Product();
        product.setActive(true);
        product.setName("Kids Bag");
        product.setPrice(Double.valueOf(800));
        product.setQuantity(Long.valueOf(100));
        category = categoryDao.getCategoryByName("A");
        product.setCategory(category);
        hibernateDao.save(product);


        product = new Product();
        product.setActive(true);
        product.setName("Grinder");
        product.setPrice(Double.valueOf(800));
        product.setQuantity(Long.valueOf(150));

        category = categoryDao.getCategoryByName("B");
        product.setCategory(category);
        hibernateDao.save(product);


        product = new Product();
        product.setActive(true);
        product.setName("Cup");
        product.setPrice(Double.valueOf(80.50));
        product.setQuantity(Long.valueOf(60));
        category = categoryDao.getCategoryByName("C");
        product.setCategory(category);
        hibernateDao.save(product);


        product = new Product();
        product.setActive(true);
        product.setName("Oil bottle");
        product.setPrice(Double.valueOf(75.25));
        product.setQuantity(Long.valueOf(200));
        category = categoryDao.getCategoryByName("A");
        product.setCategory(category);
        hibernateDao.save(product);

        //if no exception then Creation is successful.
        Assert.assertEquals("Products are created!! ", true, true);
    }

    @Test
    public void createProductOrder() {
        Long orderNo = Long.valueOf("1");

        Customer customer = customerDao.getCustomerByName("Ashok Patni");
        Set<Item> items = new HashSet<Item>();

        Order order = new Order();
        order.setStatus(OrderStatus.ORDER_ACTIVE);
        order.setCreatedDate(new Date());
        order.setCustomer(customer);
        order.setOrderNo(orderNo++);
        order.setOrderItems(items);

        Item item = new Item();
        long orderQuantity = 10;
        Product product = productDao.getProductByName("Soap");
        item.setProduct(product);
        item.setOrderedQunatity(orderQuantity);
        double itemCost = orderQuantity * product.getPrice();
        if (product.getCategory().getTax() != null)
            itemCost = itemCost + (itemCost * product.getCategory().getTax().getTaxPercent() / 100);
        item.setTotalCost(itemCost);

        //PRODUCT QUANTITY WILL BE LESS BY ORDERED QUANTITY
        product.setQuantity((product.getQuantity() - orderQuantity));
        items.add(item);


        item = new Item();
        orderQuantity = 1;
        product = productDao.getProductByName("Grinder");
        item.setProduct(product);
        item.setOrderedQunatity(orderQuantity);
        itemCost = orderQuantity * product.getPrice();
        if (product.getCategory().getTax() != null)
            itemCost = itemCost + (itemCost * product.getCategory().getTax().getTaxPercent() / 100);
        item.setTotalCost(itemCost);
        //PRODUCT QUANTITY WILL BE LESS BY ORDERED QUANTITY
        product.setQuantity((product.getQuantity() - orderQuantity));
        items.add(item);

        hibernateDao.save(order);

        //if no exception then Creation is successful.
        Assert.assertEquals("Order created!! ", true, true);

    }

    @Test
    public void generateBill() {
        Bill bill = new Bill();
        //create bill for customer and order whose status is active

        Order order = orderDao.getCustomerRecentOrder("Ashok Patni");
        bill.setOrder(order);
        bill.setDate(new Date());
        hibernateDao.save(bill);

        Set<Item> items = order.getOrderItems();
        Set<BillModel> billModelSet = new HashSet<>();

        for (Item item : items) {
            BillModel billModel = new BillModel();
            billModel.setQuantity(item.getOrderedQunatity());
            billModel.setPricePerItem(item.getProduct().getPrice());
            billModel.setCustName(order.getCustomer().getName());
            billModel.setProductName(item.getProduct().getName());
            billModel.setTotalCost(BigDecimal.valueOf(item.getTotalCost()));

            billModelSet.add(billModel);
        }

        //Display on screen

        FileWriter fileWriter = null;
        try {

            File myFile = new File(fileName);
            if (!myFile.getParentFile().exists()) {
                myFile.getParentFile().mkdirs();
            }

            fileWriter = new FileWriter(myFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("********************* BILL ****************************");
            printWriter.println("Customer Name : " + order.getCustomer().getName());
            printWriter.println("Date : " + new Date());
            printWriter.println("Product   Price/item     Quantity     Total Cost(With TAX)");
            printWriter.println("-------------------------------------------------------");
            BigDecimal total = BigDecimal.valueOf(0.0);
            for (BillModel model : billModelSet) {
                printWriter.println(model.getProductName() + "    " + model.getPricePerItem() + "         "
                        + model.getQuantity() + "         " + model.getTotalCost());
                total = total.add(model.getTotalCost());
            }

            printWriter.println("------------------------Total Cost (including tax) --------------");
            printWriter.println("               " + String.valueOf(total));
            printWriter.println("*******************************************************");

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertEquals("Bill generation failed", true, false);
        }

    }

}

