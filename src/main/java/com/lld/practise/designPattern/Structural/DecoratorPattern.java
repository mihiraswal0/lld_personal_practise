package com.lld.practise.designPattern.Structural;

// Base Component
interface Subscription {
    String getFeatures();
    double getCost();
}

// Concrete Component
 class BaseSubscription implements Subscription {
    @Override
    public String getFeatures() {
        return "Base Features";
    }

    @Override
    public double getCost() {
        return 49.99;
    }
}

// Abstract Decorator
 abstract class FeatureDecorator implements Subscription {
    protected Subscription subscription;

    public FeatureDecorator(Subscription subscription) {
        this.subscription = subscription;
    }
}

// Concrete Decorators
 class AnalyticsFeature extends FeatureDecorator {
    public AnalyticsFeature(Subscription subscription) {
        super(subscription);
    }

    @Override
    public String getFeatures() {
        return subscription.getFeatures() + ", Analytics";
    }

    @Override
    public double getCost() {
        return subscription.getCost() + 19.99;
    }
}

 class AuditLogFeature extends FeatureDecorator {
    public AuditLogFeature(Subscription subscription) {
        super(subscription);
    }

    @Override
    public String getFeatures() {
        return subscription.getFeatures() + ", Audit Logs";
    }

    @Override
    public double getCost() {
        return subscription.getCost() + 9.99;
    }
}

 class AlertFeature extends FeatureDecorator {
    public AlertFeature(Subscription subscription) {
        super(subscription);
    }

    @Override
    public String getFeatures() {
        return subscription.getFeatures() + ", Real-time Alerts";
    }

    @Override
    public double getCost() {
        return subscription.getCost() + 14.99;
    }
}

// Demo main()
class DecoraterMain {
    public static void main(String[] args) {
        Subscription subscription = new BaseSubscription();

        // Dynamically add features (e.g., Analytics and Alerts)
        subscription = new AnalyticsFeature(subscription);
        subscription = new AlertFeature(subscription);

        System.out.println("Selected Features: " + subscription.getFeatures());
        System.out.println("Total Cost: $" + subscription.getCost());
    }
}
