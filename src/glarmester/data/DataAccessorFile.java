package glarmester.data;

import java.io.FileReader;
import java.io.IOException;
import static glarmester.logic.ControllerImpl.DEBUG;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessorFile implements DataAccessor {
    private double glassPrice = Double.NaN;
    private ArrayList<Frame> frames = new ArrayList<>();
    
    private DBConnector connector = null;
    
    @Override
    public List<Frame> getFrames() throws DataException {
        
        try { 
            connector = new DBConnector();
        } catch (SQLException ex) {
            //Logger.getLogger(DataAccessorFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT frame_name, price FROM frame;";
        
        String name = "";
        double price = 0.0;
        
        try{
            Connection connection = connector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                name = rs.getString("frame_name");
                price = rs.getDouble("price");

                
                frames.add(new Frame(name, price));
            }
            
        } catch (SQLException ex) {
            
        }
        
        return frames;
    }

    
    @Override
    public Frame getFrame(String name) throws DataException {
        
        try { 
            connector = new DBConnector();
        } catch (SQLException ex) {
            //Logger.getLogger(DataAccessorFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT name, price FROM frame WHERE frame_name = '" + name + "';";
        Frame frame = null;
        
        String f_name = "";
        double price = 0.0;
        
        try {
            Connection connection = connector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                f_name = rs.getString("frame_name");
                price = rs.getDouble("price");

                
                frame = new Frame(f_name, price);
            }
            
        } catch (SQLException ex) {
            System.out.println("Hej");
            //Logger.getLogger(DataAccessObject_impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return frame;
    
    }

    @Override
    public double getGlassPrice() throws DataException {
        
        try { 
            connector = new DBConnector();
        } catch (SQLException ex) {
            //Logger.getLogger(DataAccessorFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT price FROM glass;";
        
        try {
            Connection connection = connector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                glassPrice = rs.getDouble("price");
            }
            
        } catch (SQLException ex) {
            System.out.println("Hej");
            //Logger.getLogger(DataAccessObject_impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return glassPrice;
        
    }
    
    
    /*
    private String FILENAME = "data.txt";
    
    
    public void load() throws DataException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            while((line = reader.readLine()) != null){
                line = line.toLowerCase();
                String name = line.split(":")[0].trim();
                if(name == null || name.isEmpty())
                    throw new IOException("Unable to read name!");
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                String _value = line.split(":")[1].trim();
                double value = Double.parseDouble(_value);
                if(name.toLowerCase().startsWith("glass")) {
                    glassPrice = value;
                }
                else {
                    Frame frame = new Frame(name, value);
                    frames.add(frame);
                }
            }
        } catch (IOException ex) {
            if(DEBUG)ex.printStackTrace();
            throw new DataException(ex);
        }
    }
    */
    
}
