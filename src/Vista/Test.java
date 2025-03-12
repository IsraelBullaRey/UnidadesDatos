/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.RepresentacionDatos;
/**
 *
 * @author ACER 59AL
 */
public class Test {
    public static void main(String[] args) {
        RepresentacionDatos r = new RepresentacionDatos();
        
        System.out.println(r.conversionDecimal(2048, 1, 2));
        System.out.println(r.conversionBinaria(256, 2, 1));
        System.out.println(r.conDatoDecimalByte(2.5, 2));
        System.out.println(r.conByteDecimal(2500000, 2));
        System.out.println(r.conDeciBina(2.5, 2, 1));
        System.out.println(r.conDeciBina(512, 3, 3));
        System.out.println(r.conBinaDeci(4.7, 3, 3));
        System.out.println(r.conBinaDeci(8, 6, 7));
        System.out.println(r.conBitsDecimal(100, 2, 2));
        System.out.println(r.conDecimalBits(12.5, 2, 2));
        System.out.println(r.conBitsBinario(41.94304, 2, 2));
        System.out.println(r.conBinarioBits(5, 2, 2));
    }
}
