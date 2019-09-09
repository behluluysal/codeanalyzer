/** 
 *  
 * @author Süleyman Behlül UYSAL s.b.uysal98@hotmail.com , Alperen Toklu
 * @since  26.02.2019 // Son düzenleme: 02.03.2019
 * <p> 
 *  Ödev 1 PDP 
 * </p>  
 */ 
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
/**
 *
 * @author sbuys
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filePath = null;
        JFileChooser chooser = new JFileChooser();
        int fileint = chooser.showOpenDialog(null);
        
        File file = null;
        if(fileint == JFileChooser.APPROVE_OPTION)
        {
            file = chooser.getSelectedFile();
        }
        if(file != null)
        {
            filePath = file.getPath();
        }
        
        
        File folderc = new File("calismafile");
        folderc.mkdir(); // create a folder in your current work space
        folderc.createNewFile();
        File foldert = new File(folderc,"calismatext.txt");
        foldert.createNewFile();
        FileWriter yazici = new FileWriter(foldert,true);
        BufferedWriter yaz = new BufferedWriter(yazici);
        

        
        int operatorsayac = 0;
        int fonksiyonsayac = 0;
        int parametresayac = 0;
        String[] parametretutucu = new String[100] ;
        String newparametretutucu;
        char [] parametretutucuc = new char[100];
        char [] fonksiyonisimtutucuc = new char[100];
        String[] fonksiyonisimtutucu = new String[100] ;
        boolean okuyucu = false;
        int j = 0;
        int k = 0;
        boolean okuyucu2 = false;
        // TODO code application logic here
        		BufferedReader reader;
		try {
                        boolean yaziciflag = true;
                        boolean yaziciflag2 = true;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
                        while(line != null)
                        {
                            char [] array1 = line.toCharArray();
                            for(int i=0; i<array1.length;i++)
                            {
                                if(line.contains("include"))
                                {
                                    line = reader.readLine();
                                    break;
                                }
                                if(array1[i]=='"' && yaziciflag2 == true)
                                {
                                    yaziciflag2=false; 
                                    i++;
                                }
                                else if(array1[i]=='"' && yaziciflag2 == false)
                                    yaziciflag2=true;
                                if(yaziciflag == false && array1.length <= i+1)
                                {
                                    line = reader.readLine();
                                         break; 
                                }
                                if(array1[i] == '/' && array1[i+1] == '/')
                                {
                                    line = reader.readLine();
                                         break;
                                }
                                    
                                if(array1[i] == '/' && array1[i+1] == '*')
                                    yaziciflag = false;
                                else if(array1[i] == '*' && array1[i+1] == '/')
                                {
                                    yaziciflag = true;
                                    if(i+2 < array1.length)
                                        i=i+2;
                                    else if(yaziciflag == true)
                                    {
                                        line = reader.readLine();
                                         break;
                                    }

                                }

                                if(array1[i]=='{' || array1[i] == '}')
                                {
                                    yaz.write(array1[i]);
                                    yaz.newLine();
                                }  
                                else if(yaziciflag == true && yaziciflag2 == true)
                                    yaz.write(array1[i]);
                                if((i) == (array1.length-1) && (array1[i] =='{' || array1[i]==';'))
                                {
                                    yaz.newLine();
                                    line = reader.readLine();
                                }
                                else if((i) == (array1.length-1))
                                    line = reader.readLine();      
                            }
                                if("".equals(line))
                                line = reader.readLine();
                        }
                        yaz.close();
                        reader.close();
                        
                        BufferedReader reader2;
                        reader2 = new BufferedReader(new FileReader(foldert.getAbsoluteFile()));
                         line = reader2.readLine();
			while (line != null) {
//                            String split = line.replaceAll("\\s+","");
                                String split = line;
                            char [] strarray = split.toCharArray();
                            
//                            || (split.contains("(") && split.contains(")") && split.contains("{"))
                            
                            if( ((split.contains("(") && split.contains(")") && split.contains("{"))) && (!split.contains("for") && !split.contains("if") && !split.contains("elseif") && !split.contains("else") && !split.contains("while") && !split.contains("switch") && !split.contains("case") ) )
                            {
                                fonksiyonsayac++;
                               for(int i = 0; i < strarray.length; i++){
                                   if(strarray[i] == '(')
                                   {
                                        okuyucu = true;  
                                   }
                                   if(strarray[i] == ' ')
                                   {
                                       okuyucu2 = true;
                                        split = line.replaceAll("\\s+","");
                                        strarray = split.toCharArray();
                                   }
                                       
                                   else if(( strarray[i] == '('))
                                   {
                                       j=0;
                                       okuyucu2 = false;
                                   }
                                       
                                    
                                   else if(strarray[i] == ')'){
                                       okuyucu = false;
                                       parametretutucuc[j] = ')';
                                       j=0;
                                   }
                                       
                                   if(strarray[i] == ',')
                                    parametresayac++;
                                   if(okuyucu)
                                   {
                                       parametretutucuc[j] = strarray[i];
                                       j++;
                                   }
                                   else if(okuyucu2)
                                   {
                                       fonksiyonisimtutucuc[j] = strarray[i];
                                       j++;
                                   }
                               }
//                               fonksiyonisimtutucuc=Arrays.copyOfRange(fonksiyonisimtutucuc, 2, fonksiyonisimtutucuc.length);
                                fonksiyonisimtutucu[k] = String.valueOf(fonksiyonisimtutucuc);
                                parametretutucu[k] = String.valueOf(parametretutucuc);
                                k++;
                                parametretutucuc = new char[parametretutucuc.length];
                                fonksiyonisimtutucuc = new char[fonksiyonisimtutucuc.length];
                            }
                            
                            
                            else
                            {
                            for(int i = 0; i < strarray.length; i++)
                            {
                            if(strarray[i] == '+')
                                    operatorsayac++;
                            if(strarray[i] == '-')
                                    operatorsayac++;
                            if(strarray[i] == '/')
                                    operatorsayac++;
                            if(strarray[i] == '*')
                                    operatorsayac++;
                            if(strarray[i] == '&')
                                    operatorsayac++;
                            if(strarray[i] == '=')
                                    operatorsayac++;
                            if(strarray[i] == '<')
                                operatorsayac++;
                            if(strarray[i] == '>')
                                    operatorsayac++;
                            if(strarray[i] == '!')
                                    operatorsayac++;
                            if(strarray[i] == '|')
                                    operatorsayac++;
                            } 
                            }
                            
				// read next line
				line = reader2.readLine();
			}
                        
                        
                        
                        
                        
                        parametresayac *=2;
			reader2.close();
		} catch (IOException e) {
		}
                System.out.println("Toplam operator sayisi:"+operatorsayac);
                System.out.println("Toplam fonksiyon sayisi:"+fonksiyonsayac);
                System.out.println("Toplam parametre sayisi:"+parametresayac);
                System.out.println("Fonksiyon İsimleri:");
                
                for(int i=0; i<parametretutucu.length;i++)
                {
                    if(parametretutucu[i] != null)
                    {
                        System.out.print(fonksiyonisimtutucu[i]+ " - Parametreler: "); 
                        newparametretutucu = parametretutucu[i].replace("(", "").replace(")", "").replace("int","").replace("char","").replace("string","").replace("double","").replace("float","").replace("enum","");
                        System.out.print(newparametretutucu + "\n"); 
                        
                    }

                }
                foldert.delete();
    }
    
}
