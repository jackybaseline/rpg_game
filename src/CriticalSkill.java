import java.util.Random;
public class CriticalSkill extends RSkill {
	public CriticalSkill() {		
		damage = 10;
		accuracy = 2/3;
		pp_usage = 10;
		effect = Effect.NONE;
		description="50% double attack!!";
	}
	
	public Error act(RBot src, RBot dst) {
		Random rand=new Random();
		if(rand.nextInt(10000)<(int)(10000*1/2))
			damage*=2;
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