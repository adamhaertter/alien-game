package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * Tests the mechanisms and effects of the Scope class on weapons
 * 
 * @author Brennan Mulligan
 */
public class TestScope {
  @Test
  public void TestPistolAndScope() throws AttachmentException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);
    Scope scope = new Scope(pistol);
    assertEquals(pistol.getBaseDamage(), 15);
    assertEquals(pistol.getMaxRange(), 60);
  }

  @Test
  public void TestPistolTwoScopes() throws AttachmentException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);
    
    Scope scope1 = new Scope(pistol);
    assertEquals(pistol.getBaseDamage(), 15);
    assertEquals(pistol.getMaxRange(), 60);
    
    Scope scope2 = new Scope(pistol);
    assertEquals(pistol.getBaseDamage(), 20);
    assertEquals(pistol.getMaxRange(), 70);
  }

  @Test
  public void TestChainPowerScope() throws AttachmentException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);
    assertEquals(chain.getMaxRange(), 60);
    
    PowerBooster boost = new PowerBooster(chain);
    assertEquals(chain.getBaseDamage(), 30);
    
    Scope scope = new Scope(chain);
    assertEquals(chain.getBaseDamage(), 35);
    assertEquals(chain.getMaxRange(), 70);
  }

  @Test
  public void TestPlasmaStabilizerScope() throws AttachmentException, WeaponException {
    Weapon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getShotsLeft(), 4);
    assertEquals(plasma.getMaxRange(), 40);
    
    Stabilizer stab = new Stabilizer(plasma);
    assertEquals(plasma.getBaseDamage(), 62);
    plasma.fire(10);
    plasma.fire(10);
    plasma.fire(10);
    plasma.fire(10);
    assertEquals(plasma.getShotsLeft(), 4);
    
    Scope scope= new Scope(plasma);
    assertEquals(plasma.getBaseDamage(), 72);
    assertEquals(plasma.getMaxRange(), 50);
  }
}
