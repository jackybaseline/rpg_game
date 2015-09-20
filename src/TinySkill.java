
public class TinySkill extends RSkill {
	public TinySkill() {		
		damage = 1;
		accuracy = 1/3;
		pp_usage = 1;
		effect = Effect.NONE;
		description = "Basic Attack Skill";
	}
	
	public Error act(RBot src, RBot dst) {
		if (src.getPower() >= pp_usage) {
			src.setPower(src.getPower() - pp_usage);
			dst.setHealth(Math.max(dst.getHealth() - damage, 0));
			
			return Error.NONE;
		}
		else {
			return Error.NO_POWER;
		}
	}
}