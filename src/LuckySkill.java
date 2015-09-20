import java.util.Random;
public class LuckySkill extends RSkill {
	public LuckySkill() {		
		damage = 30;
		accuracy =1/2;
		pp_usage = 5;
		effect = Effect.NONE;
		description="50% accuracy Luckyattack!!";
	}
	
	public Error act(RBot src, RBot dst) {
		Random rand=new Random();
		damage+=src.getAttack();
		damage-=dst.getDefense();
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