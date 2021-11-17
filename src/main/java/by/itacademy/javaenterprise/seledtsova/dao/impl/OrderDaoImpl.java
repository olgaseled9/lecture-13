package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

@Component("orderDaoBean")
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
    private static final String SELECT_FROM_ORDER_TABLE = "SELECT order_id, customer_id, quantity FROM Orders ORDER BY order_id LIMIT 100 OFFSET 1;";
    private static final String DELETE_ORDER_FROM_CUSTOMER_TABLES = "DELETE FROM Orders WHERE order_id = ?";
    private static final String ADD_NEW_ORDER = "INSERT INTO Orders (order_id, customer_id, quantity) VALUES (?,?,?)";
    private DataSource dataSource;

    @Override
    public Order saveOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_ORDER);
            preparedStatement.setLong(1, order.getOrderId());
            preparedStatement.setLong(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add  " + order.getClass().getName(), e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ORDER_TABLE);
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong("order_id"));
                order.setCustomerId(resultSet.getLong("customer_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                orders.add(order);
            }
        } catch (SQLException exception) {
            logger.error("Not able to add  order", exception);
        } finally {
            close(statement);
            close(connection);
        }
        return orders;
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ORDER_FROM_CUSTOMER_TABLES);
            preparedStatement.setLong(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Deleting order from database failed", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


