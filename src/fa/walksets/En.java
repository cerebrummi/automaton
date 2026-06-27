package fa.walksets;

public class En
{
   private Entity entity;
   private Nn registerN;

   public En(Entity entity, Nn registerN)
   {
      this.entity = entity;
      this.registerN = registerN;
   }

   public Entity getEntity()
   {
      return entity;
   }

   public void setEntity(Entity entity)
   {
      this.entity = entity;
   }
   
   public void setFirstSymbol()
   {
      entity = new Entity();
   }

   @Override
   public String toString()
   {
      int omegaUpperCaseHits = entity.calculateOmegaUpperCaseHits(registerN.getN());
      int liouvilleFunction = entity.calculateLiouville(omegaUpperCaseHits);
      
      return "registerE = < " + entity.getSymbol().name() + " >"
            + "\nomega lowerCase = " + entity.getOmegaLowerCaseHits() //+ "x firstHit = " + entity.isFirstHit()
            + "\nomega upperCase = " + omegaUpperCaseHits
            + "\nLiouville Function = " + liouvilleFunction;
   }
}
