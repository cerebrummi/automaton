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

   public String getCsvHeader()
   {
      return "register_e_symbol,omega_lower_case,omega_upper_case,liouville_function,";
   }
   
   @Override
   public String toString()
   {
      int omegaUpperCaseHits = entity.calculateOmegaUpperCaseHits(registerN.getN());
      int liouvilleFunction = entity.calculateLiouville(omegaUpperCaseHits);
      
      return entity.getSymbol().name()  + ","
            + entity.getOmegaLowerCaseHits() + ","
            + omegaUpperCaseHits + ","
            + liouvilleFunction;
   }
}
