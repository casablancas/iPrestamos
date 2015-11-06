/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import sun.security.util.Length;

/**
 *
 * @author Alex
 */
public class Split {
    
    public static void main (String a[])
    {
//        String material = txtMaterial.getText();
        String material = "1,2,3,4,5,";
        String [] arrayMateriales = material.split(",");
        for (int i=0; i<arrayMateriales.length; i++)
            System.out.println(arrayMateriales[i]);
    }
    
}
