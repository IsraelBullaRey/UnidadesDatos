/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author israelbr
 * @author joseluisjb  *
 */
public class RepresentacionDatos {
    
    public double conDecimalBits(double num, int dato1, int dato2) {
        double bytes = conDatoDecimalByte(num, dato1);
        double bits = byteBit(bytes);
        return conByteBinario(bits, dato2);
    }
    
    public double conBinarioBits(double num, int dato1, int dato2) {
        double bytes = conDatoBinarioByte(num, dato1);
        double bits = byteBit(bytes);
        return conByteBinario(bits, dato2);
    }
    
    public double conBitsDecimal(double num, int dato1, int dato2) {
        double bits = conDatoBinarioByte(num, dato1);
        double bytes = bitByte(bits);
        return conByteDecimal(bytes, dato2);
    }
    
    public double conBitsBinario(double num, int dato1, int dato2) {
        double bits = conDatoBinarioByte(num, dato1);
        double bytes = bitByte(bits);
        return conByteBinario(bytes, dato2);
    }
    
    public double conDeciBina(double num, int dato1, int dato2) {
        double bytes = conDatoDecimalByte(num, dato1);
        return conByteBinario(bytes, dato2);
    }
    
    public double conBinaDeci(double num, int dato1, int dato2) {
        double bytes = conDatoBinarioByte(num, dato1);
        return conByteDecimal(bytes, dato2);
    }
    
    public double conByteDecimal(double num, int dato) {
        double res = 0;
        while(dato>0) {
            res = num/1000;
        }
        return res;
    }
    
    public double conByteBinario(double num, int dato) {
        double res = 0;
        while(dato>0) {
            res = num/1024;
        }
        return res;
    }
    
    public double conDatoDecimalByte(double num, int dato) {
        double res = 0;
        while(dato>0) {
            res = num*1000;
        }
        return res;
    }
    
    public double conDatoBinarioByte(double num, int dato) {
        double res = 0;
        while(dato>0) {
            res = num*1024;
        }
        return res;
    }
    
    public double byteBit(double bytes) {
        return bytes*8;
    }
    
    public double bitByte(double bits) {
        return bits/8;
    }
}
