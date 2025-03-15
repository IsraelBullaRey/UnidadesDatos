/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 1152358 - Israel Bulla Rey
 *         1152384 - Jose Luis Jiménez Bayona
 */
public class RepresentacionDatos {
    
    public double conDecimalBits(double num, int dato1, int dato2) {
        double bytes = conDatoDecimalByte(num, dato1);
        double bits = byteBit(bytes);
        return conByteDecimal(bits, dato2);
    }
    
    public double conBinarioBits(double num, int dato1, int dato2) {
        double bytes = conDatoBinarioByte(num, dato1);
        double bits = byteBit(bytes);
        return conByteDecimal(bits, dato2);
    }
    
    public double conBitsDecimal(double num, int dato1, int dato2) {
        double bits = conDatoDecimalByte(num, dato1);
        double bytes = bitByte(bits);
        return conByteDecimal(bytes, dato2);
    }
    
    public double conBitsBinario(double num, int dato1, int dato2) {
        double bits = conDatoDecimalByte(num, dato1);
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
    
    public double conversionDecimal(double num, int dato1, int dato2) {
        validacionDatos(dato1);
        validacionDatos(dato2);
        double res = num;
        if (dato1 == dato2) return res;
        if (dato1 > dato2) {
            while (dato1 > dato2) {
                res = res*1000;
                dato1--;
            }
        }
        if (dato1 < dato2) {
            while (dato1 < dato2) {
                res = res/1000;
                dato1++;
            }
        }
        return res;
    }
    
    public double conversionBinaria(double num, int dato1, int dato2) {
        validacionDatos(dato1);
        validacionDatos(dato2);
        double res = num;
        if (dato1 == dato2) return res;
        if (dato1 > dato2) {
            while (dato1 > dato2) {
                res = res*1024;
                dato1--;
            }
        }
        if (dato1 < dato2) {
            while (dato1 < dato2) {
                res = res/1024;
                dato1++;
            }
        }
        return res;
    }
    
    public double conByteDecimal(double num, int dato) {
        validacionDatos(dato);
        double res = num;
        while(dato>0) {
            res = res/1000;
            dato--;
        }
        return res;
    }
    
    public double conByteBinario(double num, int dato) {
        validacionDatos(dato);
        double res = num;
        while(dato>0) {
            res = res/1024;
            dato--;
        }
        return res;
    }
    
    public double conDatoDecimalByte(double num, int dato) {
        validacionDatos(dato);
        double res = num;
        while(dato>0) {
            res = res*1000;
            dato--;
        }
        return res;
    }
    
    public double conDatoBinarioByte(double num, int dato) {
        validacionDatos(dato);
        double res = num;
        while(dato>0) {
            res = res*1024;
            dato--;
        }
        return res;
    }
    
    private void validacionDatos(int dato) {
        if (dato < 0 || dato > 8) {
            throw new RuntimeException("El valor que se ingresó no esta definido en el programa");
        }
    }
    
    public double byteBit(double bytes) {
        return bytes*8;
    }
    
    public double bitByte(double bits) {
        return bits/8;
    }
    
    public double conBits(double bits, int dato1, int dato2) {
        return conversionDecimal(bits, dato1, dato2);
    }
}
