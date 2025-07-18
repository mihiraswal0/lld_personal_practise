Payments are an integral component of any e-commerce or fintech system. With the advent
of Digital India, we have seen various types of payment ecosystems emerge, including
payment gateways, UPI (Unified Payments Interface), and the RuPay network. These
systems are designed for easy integration, allowing organizations to get started in a matter
of days.

Due to the wide range of payment players available, organizations often integrate multiple
payment gateways and dynamically switch between them to suit their use cases. Based on
customer preferences, payments can be made through:

Cards (credit or debit)
UPI (Virtual Payment Address)
Net banking
General Payment Process

Select a Payment Mode
Enter Payment Details:
Net Banking: Enter username and password.
Credit/Debit Card: Enter card details (card number, expiry date, CVV, etc.).
UPI: Enter the VPA (Virtual Payment Address).

Payment Gateway Design
This section outlines the design of a Payment Gateway (PG) that facilitates payments for its
clients. The design should consider the following requirements and assumptions.
Feature Requirements
Multiple Clients Support: The PG should allow multiple clients to onboard and use the
system.
Bank Integrations: The PG must support integration with multiple banks, e.g., HDFC, ICICI,
SBI.
Payment Methods: The PG should support the following payment methods:
UPI
Credit/Debit Cards
Net Banking
Payment Traffic Router: The PG must include a router that directs transactions to specific
banks based on the payment method:
E.g., All credit card transactions go to HDFC, while net banking transactions are sent to
ICICI.
Traffic Distribution: The PG should have the ability to allocate specific percentages of traffic
to different banks. For instance:
Bank 1 handles 30% of the traffic.
Bank 2 handles 70% of the traffic.

Assumptions
Bank Response Simulation: Each bank can return either success or failure randomly. A
function should be created to simulate this behavior.
Required Payment Details: Payments should only be processed if the necessary parameters
for the chosen payment method are provided. For example:
Net banking requires a username and password.
Credit cards require card details (number, expiry date, and CVV).
OTP Verification: Although OTP verification is usually required after payment details are
provided, for simplicity, transactions will proceed without OTP verification.

Code Expectations
The implementation should be in-memory.
Simple and basic functions should be created as entry points; no marks will be awarded for
implementing a RESTful API or a complete framework.
Required Functions
1. showDistribution(): This function should display the current distribution percentage of
   payment traffic across the integrated banks.
2. makePayment(): This function should process a payment based on the provided payment
   details. Different methods (e.g., UPI, cards, net banking) should be handled accordingly.
   Expectations
   ● You can implement the functionality in one of the programming languages (Java,
   Golang, Ruby, JS, or Python)
   ● Please don’t create REST APIs or any UI for the same.
   ● You can test the functionality using unit test cases.
   ● The focus is on the core functionality and how it is implemented v/s how it is
   presented (UI or API structure).
   ● Please use the coding best practices (SOLID, DRY, etc.) and ensure that you are
   writing modular & extensible code (to accommodate any future changes in functionality)


Solutions

1. Payment Gateway
2. User 
   a. makePayment()
3. Bank
   a. addClient
        supports type of payment
        count of total traffic  it supports
4. Payment Methods
   a. UPI
    b. Credit Card
   c. NetBanking

Flows:

1. Initalize payment Gateways
2. Initalize payment methods
3. Add client-> bank name->list<paymentMethod> supported -> Traffic for individual payment Method: (How will redistirbution work (Redistribution strategy))
4. User
   5. select paymentMode
   6. provide all infromation
   7. Validate information provided
   8. 

Entities

Inteface PaymentMode
1. Card
   cardNumber
   cardName
    expiryDate
    CVV

2. UPI
   upiId

3. NetBanking

Use Factory Design to create these PaymentMode


BankClient
    a. clientName: BankName
    b. List<PaymentMode> modes
    c. Map<PaymentMode,trafficCount> modeTraffic
    d. successFullPayment
    e. failedPayment


Service Layer


PaymentRouter
    Map<PaymentMode,List<BankClient>>
    ClientSelectionStrategy


     

ClientManager
    manages all the client
    a. Map<clientId,BankClient>clientDetails
    add Client
    add ModeClient
    modiyTraffic
    findClient

TrafficDistributionStrategy


PaymentGateway
    ClientManager 
    payementMethod()
    choose type of paymentMode
    vaildateInformation
    performSuccessfullpayment
    performFailurePayment
    
    

    






    



