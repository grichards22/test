/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.operator;

/**
 *
 * @author delta
 */
public class Operator {

    private double[][] elements = new double[2][2];

    public Operator() {
        this.elements[0][0] = 1.0;
        this.elements[0][1] = 0.0;
        this.elements[1][0] = 0.0;
        this.elements[1][1] = 1.0;
    }
    
    public Operator(double m00, double m01, double m10, double m11) {
        this.elements[0][0] = m00;
        this.elements[0][1] = m01;
        this.elements[1][0] = m10;
        this.elements[1][1] = m11;
    }
    
    public void rotation (double angle){
        this.elements[0][0] = Math.cos(angle);
        this.elements[0][1] = -Math.sin(angle);
        this.elements[1][0] = Math.sin(angle);
        this.elements[1][1] = Math.cos(angle);
    } //rotation(double)
    
    
    public Operator multiply(Operator otherOperator){
        double left00 = this.elements[0][0];
        double left01 = this.elements[0][1];
        double left10 = this.elements[1][0];
        double left11 = this.elements[1][1];
        
        double right00 = otherOperator.elements[0][0];
        double right01 = otherOperator.elements[0][1];
        double right10 = otherOperator.elements[1][0];
        double right11 = otherOperator.elements[1][1];
        
        double p00 = left00 * right00 + left01 * right10;
        double p01 = left00 * right01 + left01 * right01;
        double p10 = left10 * right00 + left11 * right10;
        double p11 = left10 * right01 + left11 * right11;
        return new Operator(p00, p01, p10, p11);
    } //multiply (Operator)
    @Override
    public String toString(){
        double m00 = this.elements[0][0];
        double m01 = this.elements[0][1];
        double m10 = this.elements[1][0];
        double m11 = this.elements[1][1];
        String row0 = "(" + m00 + "," + m01 + ")";
        String row1 = "(" + m10 + "," + m11 + ")";
        return "[" + row0 + "," + row1 + "]";
    }//toString
    
    public static void main(String[] args) {
        Operator identity = new Operator();
        System.out.println("Identity = " + identity);
        
        Operator product = identity.multiply(identity);
        System.out.println("Product = " + product);
        
        Operator ccw = new Operator();
        ccw.rotation(Math.PI/4);
        
        Operator cw = new Operator();
        cw.rotation(-Math.PI/4);
        
        Operator totalRotation = ccw.multiply(cw);
        
        System.out.println("ccw = " + ccw);
        System.out.println("cw = " + cw);
        System.out.println("total rotation = " + totalRotation);
    }//main(String[] )
} //Operator
