package com.github.pfacheris.BukkitDuel.Handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.github.pfacheris.BukkitDuel.BukkitDuel;
import com.github.pfacheris.BukkitDuel.Objects.Duel;

public class DuelMgr {

	private BukkitDuel plugin;
	private List<Duel> duels;

	public DuelMgr(BukkitDuel plugin)
	{
		this.plugin = plugin;
		duels = new ArrayList<Duel>();
		duels.clear();
	}

	public List<Duel> getActiveDuels()
	{
		List<Duel> activeDuels =  new ArrayList<Duel>();
		for(Duel d : duels)
		{
			if (d.getIsActive())
			{
				activeDuels.add(d);
			}
		}

		return activeDuels;
	}

	public List<Duel> getPendingDuels()
	{
		List<Duel> pendingDuels =  new ArrayList<Duel>();
		for(Duel d : duels)
		{
			if (!d.getIsActive())
			{
				pendingDuels.add(d);
			}
		}

		return pendingDuels;
	}

	public Duel getDuelByTwoParticipants(Player initiator, Player challengee)
	{
		if (duels.size() > 0)
		{
			for(Duel d : duels)
			{
				if (d.getChallengee().equals(challengee) || d.getInitiator().equals(initiator))
				{
					return d;
				}
				if (d.getChallengee().equals(initiator) || d.getInitiator().equals(challengee))
				{
					return d;
				}
			}
		}
		return null;
	}

	public List<Duel> getDuelByParticipant(Player participant)
	{
		List<Duel> duelsWithParticipant =  new ArrayList<Duel>();
		for(Duel d : duels)
		{
			if (d.getChallengee().equals(participant) || d.getInitiator().equals(participant))
			{
				duelsWithParticipant.add(d);
			}
		}

		return duelsWithParticipant;
	}

	public boolean isPlayerInActiveDuel(Player participant)
	{
		for(Duel d : duels)
		{
			if ((d.getChallengee().equals(participant) || d.getInitiator().equals(participant)) && d.getIsActive())
			{
				return true;
			}
		}

		return false;
	}

	public List<Duel> getDuelByInitiator(Player participant)
	{
		List<Duel> duelsWithParticipant =  new ArrayList<Duel>();
		for(Duel d : duels)
		{
			if (d.getInitiator().equals(participant))
			{
				duelsWithParticipant.add(d);
			}
		}

		return duelsWithParticipant;
	}

	public List<Duel> getDuelByChallengee(Player participant)
	{
		List<Duel> duelsWithParticipant =  new ArrayList<Duel>();
		for(Duel d : duels)
		{
			if (d.getChallengee().equals(participant))
			{
				duelsWithParticipant.add(d);
			}
		}

		return duelsWithParticipant;
	}

	public void save(Duel newDuel)
	{
		duels.add(newDuel);
	}

	public void cancelDuel(Duel cancelledDuel)
	{
		duels.remove(cancelledDuel);
	}

	public void endDuel(Duel concludedDuel, boolean initiatorWins)
	{
		duels.remove(concludedDuel);
		concludedDuel.endDuel(plugin,initiatorWins);
		if (getActiveDuels().size() < 1)
			HandlerList.unregisterAll(plugin);
	}
}
