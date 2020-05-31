import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Calisanİslemleri {
 
    private Connection con =null;
    private Statement statement=null;
    
    private PreparedStatement preparedStatement =null;
    
    public ArrayList<Calisan> calisanlariGetir(){
        ArrayList<Calisan> cikti =new ArrayList<Calisan>();
        
        try {
            statement=con.createStatement();
            String sorgu ="Select * From calisanlar";
            ResultSet rs =statement.executeQuery(sorgu);    
            while (rs.next()) {                
                int id =rs.getInt("id");
                String ad=rs.getString("ad");
                String soyad=rs.getString("soyad");
                String departman=rs.getString("departman");
                String maas=rs.getString("maas");
                
                cikti.add(new Calisan(id, ad, soyad, departman, maas));
                
            }
            return cikti;
            
        } catch (SQLException ex) {
            Logger.getLogger(Calisanİslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public void calisanGüncelle(int id,String ad,String soyad,String departman,String maas){
        
        String sorgu="Update calisanlar set ad = ? , soyad = ? , departman = ? , maas = ? where id = ?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,ad);
            preparedStatement.setString(2,soyad);
            preparedStatement.setString(3,departman);
            preparedStatement.setString(4,maas);
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
            
                    
                    
                    
        } catch (SQLException ex) {
            Logger.getLogger(Calisanİslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void calisanSil(int id){
        String sorgu ="Delete from calisanlar where id = ?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Calisanİslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void calisanEkle(String ad,String soyad,String departman,String maas){
        String sorgu ="Insert Into calisanlar (ad,soyad,departman,maas) VALUES(?,?,?,?)";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,ad);
            preparedStatement.setString(2,soyad);
            preparedStatement.setString(3,departman);
            preparedStatement.setString(4,maas);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Calisanİslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean girisYap(String kullanici_adi,String parola){
       
        String sorgu ="Select * From adminler where username = ? and password = ?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs =preparedStatement.executeQuery();
            
            return rs.next();

            
            
        } catch (SQLException ex) {
            return false;
        }
        
        
    }
    public Calisanİslemleri(){
           
        String url = "jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db_ismi+"?useUnicode=true&characterEncoding=utf8";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadi...");
        }
        
        try {
            con = DriverManager.getConnection(url,Database.kullaniciAdi, Database.parola);
        } catch (SQLException ex) {
            System.out.println(" VeriTabani Baglantisi Kurulamadi...");
        }
    }
}
    

