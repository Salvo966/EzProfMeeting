import myJava.model.beans.Professore;
import myJava.model.beans.Studente;
import myJava.model.beans.User;
import myJava.model.general.AccessManager;
import org.junit.Assert;
import org.junit.Test;


import java.sql.SQLException;

public class AccessMangerTest { 

    @Test
    public void testDoLogin()throws SQLException {
        AccessManager am=new AccessManager();
        User user=new User("mia@email.it","miapassword2","studente");
        Assert.assertEquals(user.getEmail(),am.doLogin("mia@email.it","miapassword2").getEmail());

        Assert.assertEquals(user.getPassword(),am.doLogin("mia@email.it","miapassword2").getPassword());
      Assert.assertEquals(user.getTipo(),am.doLogin("mia@email.it","miapassword2").getTipo());
        Assert.assertNull(am.doLogin("mia@email.it","albero"));

        Assert.assertNull(am.doLogin("email@mia.it","abcdefrtew"));


    }
    @Test
    public void testGetProfessoreByEmail()throws SQLException{
        AccessManager am=new AccessManager();
        Professore professore=new Professore(1,"Filomena","Ferrucci","fferrucci@unisa.it","0819876543","stecca F");
        Assert.assertEquals(professore.getIdProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getIdProfessore());
        Assert.assertEquals(professore.getNomeProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getNomeProfessore());
        Assert.assertEquals(professore.getCognomeProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getCognomeProfessore());
        Assert.assertEquals(professore.getEmailProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getEmailProfessore());
        Assert.assertEquals(professore.getTelefonoProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getTelefonoProfessore());
        Assert.assertEquals(professore.getUfficioProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getUfficioProfessore());
        Assert.assertNull(am.getProfessoreByEmail("sadsadsada"));
        Assert.assertNull(am.getProfessoreByEmail("albero@bello.it"));

    }

    @Test
    public void testGetStudenteByEmail() throws SQLException{
        AccessManager am=new AccessManager();
       Studente stu=new Studente(1,"Salvatore","Santelia","0512104001","s.santelia1@studenti.unisa.it","3334455678",5);
        Assert.assertEquals(stu.getIdStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getIdStudente());
        Assert.assertEquals(stu.getNomeStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getNomeStudente());
        Assert.assertEquals(stu.getCognomeStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getCognomeStudente());
        Assert.assertEquals(stu.getMatricola(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getMatricola());
        Assert.assertEquals(stu.getEmailStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getEmailStudente());
        Assert.assertEquals(stu.getTelefonoStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getTelefonoStudente());
        Assert.assertNull(am.getStudenteByEmail("dsadasdsdasdsad"));
        Assert.assertNull(am.getStudenteByEmail("studente@mail.it"));
    }
}
