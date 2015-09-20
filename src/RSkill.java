enum Effect {NONE, POISON, BURN, FREEZE, PARALYSIS, SLEEP}
enum Error {NONE, NO_POWER, MISS}
public abstract class RSkill {
	protected Attribute attribute;
	protected int damage;
	protected int special_damage;
	protected int critical_chance;
	protected double accuracy;
	protected int hp_regen;
	protected int hp_usage;
	protected int pp_regen;
	protected int pp_usage;
	protected Effect effect;
	protected int effect_accuracy;
	protected String description;
	
	public abstract Error act(RBot src, RBot dst);
}