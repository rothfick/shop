package org.ecommerceshop.service;

import jakarta.transaction.Transactional;
import org.ecommerceshop.model.Order;
import org.ecommerceshop.model.User;
import org.ecommerceshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final String ADMIN_EMAIL = "robercikkx@gmail.com";

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order purchaseOrder(Order order) {
        // Ustawienie użytkownika na podstawie OAuth2
        OidcUser oidcUser = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = oidcUser.getEmail();
        User user = new User();
        user.setEmail(email);
        order.setUser(user);

        // Ustawienie daty zamówienia
        order.setOrderDate(LocalDateTime.now());

        // Zapisanie zamówienia w bazie danych
        Order savedOrder = orderRepository.save(order);

        // Wysyłanie potwierdzenia mejlowego
        sendOrderConfirmationEmail(savedOrder);

        // Wysyłanie informacji o zamówieniu do administratora
        sendAdminNotificationEmail(savedOrder);

        return savedOrder;
    }

    private void sendOrderConfirmationEmail(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getUser().getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Thank you for your purchase! Your order number is " + order.getId());

        mailSender.send(message);
    }

    private void sendAdminNotificationEmail(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(ADMIN_EMAIL);
        message.setSubject("New Order Notification");
        message.setText("A new order has been placed. Order number: " + order.getId() + "\nUser email: " + order.getUser().getEmail());

        mailSender.send(message);
    }
}
