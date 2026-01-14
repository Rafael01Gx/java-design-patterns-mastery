package com.rafaelmoraes.creational.builder.example01;

public class Order {

    // Campos obrigatórios
    private final String orderId;
    private final String customerName;

    // Campos opcionais
    private final String customerEmail;
    private final String customerPhone;
    private final String shippingAddress;
    private final String billingAddress;
    private final String paymentMethod;
    private final double totalAmount;
    private final double discount;
    private final double shippingCost;
    private final String couponCode;
    private final String notes;
    private final boolean giftWrap;
    private final String giftMessage;
    private final boolean expressShipping;


    // CONSTRUTOR PRIVADO - Só pode ser chamado pelo Builder
    private Order(Builder builder) {
        // Validações
        if (builder.orderId == null || builder.orderId.isEmpty()) {
            throw new IllegalStateException("Order ID é obrigatório");
        }
        if (builder.customerName == null || builder.customerName.isEmpty()) {
            throw new IllegalStateException("Nome do cliente é obrigatório");
        }

        // Atribuições
        this.orderId = builder.orderId;
        this.customerName = builder.customerName;
        this.customerEmail = builder.customerEmail;
        this.customerPhone = builder.customerPhone;
        this.shippingAddress = builder.shippingAddress;
        this.billingAddress = builder.billingAddress;
        this.paymentMethod = builder.paymentMethod;
        this.totalAmount = builder.totalAmount;
        this.discount = builder.discount;
        this.shippingCost = builder.shippingCost;
        this.couponCode = builder.couponCode;
        this.notes = builder.notes;
        this.giftWrap = builder.giftWrap;
        this.giftMessage = builder.giftMessage;
        this.expressShipping = builder.expressShipping;
    }


    // GETTERS (Objeto é imutável - sem setters)

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public String getGiftMessage() {
        return giftMessage;
    }

    public boolean isExpressShipping() {
        return expressShipping;
    }


    // BUILDER CLASS - CLASSE INTERNA ESTÁTICA
    public static class Builder {

        // Campos obrigatórios
        private String orderId;
        private String customerName;

        // Campos opcionais com valores padrão
        private String customerEmail;
        private String customerPhone;
        private String shippingAddress;
        private String billingAddress;
        private String paymentMethod;
        private double totalAmount = 0.0;
        private double discount = 0.0;
        private double shippingCost = 0.0;
        private String couponCode;
        private String notes;
        private boolean giftWrap = false;
        private String giftMessage;
        private boolean expressShipping = false;


        // CONSTRUTOR DO BUILDER COM CAMPOS OBRIGATÓRIOS
       public Builder(String orderId, String customerName) {
            this.orderId = orderId;
            this.customerName = customerName;
        }


        // MÉTODOS FLUENTES (RETORNAM 'this' PARA ENCADEAMENTO)
        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerEmail(String email) {
            this.customerEmail = email;
            return this;
        }

        public Builder customerPhone(String phone) {
            this.customerPhone = phone;
            return this;
        }

        public Builder shippingAddress(String address) {
            this.shippingAddress = address;
            return this;
        }

        public Builder billingAddress(String address) {
            this.billingAddress = address;
            return this;
        }

        public Builder paymentMethod(String method) {
            this.paymentMethod = method;
            return this;
        }

        public Builder totalAmount(double amount) {
            this.totalAmount = amount;
            return this;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder shippingCost(double cost) {
            this.shippingCost = cost;
            return this;
        }

        public Builder couponCode(String code) {
            this.couponCode = code;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder giftWrap(boolean giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public Builder giftMessage(String message) {
            this.giftMessage = message;
            this.giftWrap = true; // Se tem mensagem, é presente
            return this;
        }

        public Builder expressShipping(boolean express) {
            this.expressShipping = express;
            return this;
        }


        // MÉTODO BUILD - CONSTRÓI E RETORNA O OBJETO FINAl
        public Order build() {
            return new Order(this);
        }
    }
}