import java.util.Random;
public class Regardless_def_Skill extends RSkill {
	public Regardless_def_Skill() {		
		damage = 20;
		accuracy =2/3;
		pp_usage = 7;
		effect = Effect.NONE;
		description="Regardless defense attack!!!";
	}
	
	public Error act(RBot src, RBot dst) {
		Random rand=new Random();
		damage+=src.getAttack();
		if(rand.nextInt(10000)<(int)(10000*accuracy))
		{
			if (src.getPower() >= pp_usage) {
			src.setPower(src.getPower() - pp_usage);
			dst.setHealth(Math.max(dst.getHealth() - damage, 0));
			
			return Error.NONE;
			}
			else {
				return Error.NO_POWER;
			}
		}
		else
			return Error.MISS;
	}
}