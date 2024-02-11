import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameData {
    protected final List<Knight> activeKnights = new ArrayList<Knight>();
    protected final List<Knight> knights = new ArrayList<Knight>();
    protected final List<Fortune> fortunes = new ArrayList<Fortune>();
    protected final static int MAX_ACTIVE = 4;
    private static final Random random = new Random();
    protected final List<MOB> monsters = new ArrayList<MOB>();

    public GameData() {
    }

    protected Knight findKnight(String nameOrId, List<Knight> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().toString().equals(nameOrId) || list.get(i).getName().toLowerCase().contains(nameOrId)) {
                return list.get(i);
            }
        }
        return null;
    }

    public Knight getActive(String nameOrId) {
        return findKnight(nameOrId, getActiveKnights());
    }

    public Knight getKnight(String nameOrId) {
        if (findKnight(nameOrId, getKnights()) == null) {
            return null;
        }
        else {
            return findKnight(nameOrId, getKnights());
        }
    }

    public List<Knight> getActiveKnights() {
        return activeKnights;
    }

    public List<Knight> getKnights() {
        return knights;
    }

    public List<MOB> getRandomMonsters() {
        return getRandomMonsters(random.nextInt(activeKnights.size() + 1));
    }

    public List<MOB> getRandomMonsters(int number)
    {
        List<MOB> temp = new ArrayList<MOB>();
        MOB tmp;
        for(int i = 0; i < number; i++)
        {
            tmp = monsters.get(random.nextInt(monsters.size()));
            temp.add(tmp.copy());
        }
        return temp;
    }

    public void removeActive(Knight kt) {
        boolean knightRemoval = activeKnights.remove(kt);
        if (knightRemoval == true) {
            kt.resetDamage();
        }
    }

    public Fortune getRandomFortune() {
        int fortuneSize = fortunes.size();
        int randomFortune = random.nextInt(fortuneSize);
        return fortunes.get(randomFortune);
    }

    public boolean setActive(Knight kt) {
        if (activeKnights.size() >= MAX_ACTIVE) {
            return false;
        }
        else {
            activeKnights.add(kt);
            return true;
        }
    }

    public abstract void save(String filename);
}
