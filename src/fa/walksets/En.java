package fa.walksets;

public class En
{
   private Entity entity;

   public En(Entity entity)
   {
      this.entity = entity;
   }

   public Entity getEntity()
   {
      return entity;
   }

   public void setEntity(Entity entity)
   {
      this.entity = entity;
   }

   @Override
   public String toString()
   {
      return "registerE = < " + entity.getSymbol().name() + " "
            + entity.getOmegaLowerCaseHits() + "x firstHit = " + entity.isFirstHit() + " >";
   }

   public void setFirstSymbol()
   {
      entity = new Entity();
   }
}
