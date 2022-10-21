package SailPoint.com;
import sailpoint.api.PersistenceManager;
import sailpoint.api.SailPointContext;
import sailpoint.api.SailPointFactory;
import sailpoint.object.Identity;
import sailpoint.object.ProvisioningResult;
import sailpoint.spring.SpringStarter;
import sailpoint.tools.GeneralException;
import sailpoint.object.Filter;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import sailpoint.object.ProvisioningPlan;
import sailpoint.object.ProvisioningPlan.AccountRequest;

public class spClient {

       private static final ProvisioningResult ProvisioningResult = null;

	public SailPointContext getSailpointContext() {
              SailPointContext context =null;
              String override=null;
              SpringStarter ss= new SpringStarter ("iiqBeans.xml",override);
              String configFile=ss.getConfigFile();
              System.out.println("config File::"+configFile);
              String[] services = {"Task","Request"};
              //SpringStarter.setSuppressedServices(services);
              SpringStarter.suppressSchedulers();
              SpringStarter.setSuppressVersionChecker(true);
              ss.start();
              System.out.println("HERE");
              System.out.println("Demo");

              try {
                     context= SailPointFactory.createContext("identityiq");
                     if(context !=null) {
                           System.out.println("Got Connection "+context);
                           context.authenticate("spadmin", "admin");
                     }else {
                           System.out.println("null Connection ");
                     }
              }catch(Exception e) {
                     e.printStackTrace();
              }
              return context;
       }
       private Identity searchIdentity (SailPointContext context, String identiyName) {
              Identity identity =null;
              Boolean inactivityflag=true;
             

              try {
                     identity= context.getObject(Identity.class,identiyName);
                     System.out.println("First Name::"+identity.getFirstname());
                     System.out.println("Last Name::"+identity.getLastname());
                     System.out.println("Email::"+identity.getEmail());
                     inactivityflag=(Boolean) identity.getAttribute("inactive");
                     System.out.println("inactivityflag::"+inactivityflag);
              } catch (GeneralException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              }
              return identity;
       }
     
       public Map<String, String>assertionAttributes (String NameId, String nameid) throws GeneralException
       {
    	   Map<String, String> assertion = new HashMap<>();
    	   assertion.put(NameId, nameid);
    	   System.out.println(assertion.put(NameId, nameid));
    	   //SailPointContext context1=getSailpointContext();
		//coorelateToIdenity(context1,assertion);
    	   return assertion;
       }

       
       public static void main (String[] args)throws Exception {
          String path= "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\identityiq"; // provide the path from your install Directory
              System.setProperty("SPHOME",path);
              System.setProperty("SP_HOME",path); 
              System.setProperty("sailpoint.home",path);
              SailPointContext context =null;
              System.out.println("Establishing Connection ");
              spClient sc=new spClient();
              context =sc.getSailpointContext();
             // System.out.println(plan.t);
             sc.searchIdentity(context,"karimiharish493@gmail.com");
             
             // sc.assertionAttributes("Harish", "karimiharish493@gmail.com");
              
              try {
                     context.close();
              } catch (GeneralException e) {
                     e.printStackTrace();
              }
       }

}