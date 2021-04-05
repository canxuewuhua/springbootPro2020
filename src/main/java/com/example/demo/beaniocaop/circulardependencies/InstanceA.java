package com.example.demo.beaniocaop.circulardependencies;

public class InstanceA {

    private InstanceB instanceB;

    public InstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }
}
