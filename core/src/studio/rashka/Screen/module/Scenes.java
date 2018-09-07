package studio.rashka.Screen.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import studio.rashka.Models.food.Goat;
import studio.rashka.Models.food.Kine;
import studio.rashka.Models.food.Pig;
import studio.rashka.Models.obstacle.Casket;
import studio.rashka.Models.obstacle.rock.Rock1;
import studio.rashka.Models.obstacle.rock.Rock2;
import studio.rashka.Models.obstacle.rock.Rock3;
import studio.rashka.Models.obstacle.rock.Rock4;
import studio.rashka.Models.obstacle.rock.Rock5;
import studio.rashka.Models.obstacle.rock.Rock6;
import studio.rashka.Models.obstacle.rock.Rock7;
import studio.rashka.Models.obstacle.tree.Ash;
import studio.rashka.Models.obstacle.tree.Aspen;
import studio.rashka.Models.obstacle.tree.Birch;
import studio.rashka.Models.obstacle.tree.Fir;
import studio.rashka.Models.obstacle.tree.Linden;
import studio.rashka.Models.obstacle.tree.Maple;
import studio.rashka.Models.obstacle.tree.Oak;
import studio.rashka.Models.obstacle.tree.Pine;
import studio.rashka.Models.obstacle.tree.Poplar;
import studio.rashka.Models.obstacle.tree.Spruce;
import studio.rashka.Models.obstacle.tree.Willow;
import studio.rashka.RuDragonGame;

public class Scenes {
    private Kine kine;
    private Goat goat;
    private Pig pig;

    private Casket casket;

    private Ash ash; // деревья
    private Aspen aspen;
    private Birch birch;
    private Fir fir;
    private Linden linden;
    private Maple maple;
    private Oak oak;
    private Pine pine;
    private Poplar poplar;
    private Spruce spruce;
    private Willow willow;

    private Rock1 rock1; // горы
    private Rock2 rock2;
    private Rock3 rock3;
    private Rock4 rock4;
    private Rock5 rock5;
    private Rock6 rock6;
    private Rock7 rock7;

    public Scenes() {
        kine = new Kine();
        goat = new Goat();
        pig = new Pig();

        casket = new Casket();

        ash = new Ash();
        aspen = new Aspen();
        birch = new Birch();
        fir = new Fir();
        linden = new Linden();
        maple = new Maple();
        oak = new Oak();
        pine = new Pine();
        poplar = new Poplar();
        spruce = new Spruce();
        willow = new Willow();

        rock1 = new Rock1();
        rock2 = new Rock2();
        rock3 = new Rock3();
        rock4 = new Rock4();
        rock5 = new Rock5();
        rock6 = new Rock6();
        rock7 = new Rock7();
    }

    public void loadScene(int indexScene) {
        if (indexScene == 0 || indexScene == 1) {
            Gdx.app.log("", "1");
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(10);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(480);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(800);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1056);
            }
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(1654);
            }
        }
        else if (indexScene == 2) {
            Gdx.app.log("", "2");
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(16);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(584);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1138);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(1344);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1700);
            }
        }
        else if (indexScene == 3) {
            Gdx.app.log("", "3");
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(64);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(384);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(850);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(1270);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(1728);
            }
        }
        else if (indexScene == 4) {
            Gdx.app.log("", "4");
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(32);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(256);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(512);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(832);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(1344);
            }
        }
        else if (indexScene == 5) {
            Gdx.app.log("", "5");
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(8);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(128);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(736);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(1024);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1220);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1576);
            }
        }
        else if (indexScene == 6) {
            Gdx.app.log("", "6");
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(10);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(192);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(700);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(960);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(1312);
            }
        }
        else if (indexScene == 7) {
            Gdx.app.log("", "7");
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(12);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(384);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(960);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(1152);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(1728);
            }
        }
        else if (indexScene == 8) {
            Gdx.app.log("", "8");
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(32);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(448);
            }
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(832);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1088);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(1600);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1697);
            }
        }
        else if (indexScene == 9) {
            Gdx.app.log("", "9");
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(10);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(256);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(716);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(960);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1228);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1536);
            }
        }
        else if (indexScene == 10) {
            Gdx.app.log("", "10");
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(32);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(320);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(832);
            }
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(880);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(1088);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1408);
            }
        }
        else if (indexScene == 11) {
            Gdx.app.log("", "11");
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(0);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(640);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(788);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1045);
            }
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(1664);
            }
        }
        else if (indexScene == 12) {
            Gdx.app.log("", "12");
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(32);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(256);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(768);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1600);
            }
        }
        else if (indexScene == 13) {
            Gdx.app.log("", "13");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(828);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(1128);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(1664);
            }
        }
        else if (indexScene == 14) {
            Gdx.app.log("", "14");
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(5);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(256);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(608);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(896);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1440);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1728);
            }
        }
        else if (indexScene == 15) {
            Gdx.app.log("", "15");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(748);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(864);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1344);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(1536);
            }
        }
        else if (indexScene == 16) {
            Gdx.app.log("", "16");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(640);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1152);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1600);
            }
        }
        else if (indexScene == 17) {
            Gdx.app.log("", "17");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(768);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1290);
            }
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(1457);
            }
        }
        else if (indexScene == 18) {
            Gdx.app.log("", "18");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(768);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(1344);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(1487);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1600);
            }
        }
        else if (indexScene == 19) {
            Gdx.app.log("", "19");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(380);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(512);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(1088);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(1344);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1560);
            }
        }
        else if (indexScene == 20) {
            Gdx.app.log("", "20");
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(0);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(686);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(932);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1124);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(1472);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(1664);
            }
        }
        else if (indexScene == 21) {
            Gdx.app.log("", "21");
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(0);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(384);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(545);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(700);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(1216);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(1472);
            }
        }
        else if (indexScene == 22) {
            Gdx.app.log("", "22");
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(128);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(576);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(1472);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(1790);
            }
        }
        else if (indexScene == 23) {
            Gdx.app.log("", "23");
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(0);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(192);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(672);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(800);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1216);
            }
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(1664);
            }
        }
        else if (indexScene == 24) {
            Gdx.app.log("", "24");
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(0);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(128);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(576);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(832);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(1088);
            }
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(1344);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1536);
            }
        }
        else if (indexScene == 25) {
            Gdx.app.log("", "25");
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(960);
            }
        }
        else if (indexScene == 26) {
            Gdx.app.log("", "26");
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(960);
            }
        }
        else if (indexScene == 27) {
            Gdx.app.log("", "27");
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(960);
            }
        }
        else if (indexScene == 28) {
            Gdx.app.log("", "28");
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(0);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(384);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(576);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(896);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(1408);
            }
        }
        else if (indexScene == 29) {
            Gdx.app.log("", "29");
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(4);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(320);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(704);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1344);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(1408);
            }
        }
        else if (indexScene == 30) {
            Gdx.app.log("", "30");
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(32);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(384);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1200);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(1408);
            }
        }
        else if (indexScene == 31) {
            Gdx.app.log("", "31");
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(64);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(384);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(612);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1152);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(1408);
            }
        }
        else if (indexScene == 32) {
            Gdx.app.log("", "32");
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(0);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(285);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(448);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(960);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(1472);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1728);
            }
        }
        else if (indexScene == 33) {
            Gdx.app.log("", "33");
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(0);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(384);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(896);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1280);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1472);
            }
        }
        else if (indexScene == 34) {
            Gdx.app.log("", "34");
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(0);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(192);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(576);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1152);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(1280);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1600);
            }
        }
        else if (indexScene == 35) {
            Gdx.app.log("", "35");
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(0);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(310);
            }
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(896);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1216);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1664);
            }
        }
        else if (indexScene == 36) {
            Gdx.app.log("", "36");
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(0);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(384);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(768);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1216);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1664);
            }
        }
        else if (indexScene == 37) {
            Gdx.app.log("", "37");
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(0);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(320);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(640);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(1024);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1600);
            }
        }
        else if (indexScene == 38) {
            Gdx.app.log("", "38");
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(0);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(320);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(512);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(1152);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(1408);
            }
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(1600);
            }
        }
        else if (indexScene == 39) {
            Gdx.app.log("", "39");
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(64);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(256);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(380);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(640);
            }
            if (!maple.isGo()) {
                maple.setGo(true);
                maple.setAddX(1152);
            }
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(1408);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1600);
            }
        }
        else if (indexScene == 40) {
            Gdx.app.log("", "40");
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(32);
            }
            if (!oak.isGo()) {
                oak.setGo(true);
                oak.setAddX(256);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(768);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(1048);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1424);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1800);
            }
        }
        else if (indexScene == 41) {
            Gdx.app.log("", "41");
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(64);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(448);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(768);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1344);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1800);
            }
        }
        else if (indexScene == 42) {
            Gdx.app.log("", "42");
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(64);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(256);
            }
            if (!pig.isGo()) {
                pig.setGo(true);
                pig.setAddX(704);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1024);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(1408);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1800);
            }
        }
        else if (indexScene == 43) {
            Gdx.app.log("", "43");
            if (!ash.isGo()) {
                ash.setGo(true);
                ash.setAddX(0);
            }
            if (!pine.isGo()) {
                pine.setGo(true);
                pine.setAddX(256);
            }
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(448);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(768);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(1280);
            }
            if (!poplar.isGo()) {
                poplar.setGo(true);
                poplar.setAddX(1756);
            }
        }
        else if (indexScene == 44) {
            Gdx.app.log("", "44");
            if (!spruce.isGo()) {
                spruce.setGo(true);
                spruce.setAddX(64);
            }
            if (!fir.isGo()) {
                fir.setGo(true);
                fir.setAddX(256);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(512);
            }
            if (!linden.isGo()) {
                linden.setGo(true);
                linden.setAddX(1088);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(1664);
            }
        }
        else if (indexScene == 45) {
            Gdx.app.log("", "45");
            if (!aspen.isGo()) {
                aspen.setGo(true);
                aspen.setAddX(0);
            }
            if (!kine.isGo()) {
                kine.setGo(true);
                kine.setAddX(384);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(544);
            }
            if (!willow.isGo()) {
                willow.setGo(true);
                willow.setAddX(704);
            }
            if (!goat.isGo()) {
                goat.setGo(true);
                goat.setAddX(1216);
            }
            if (!birch.isGo()) {
                birch.setGo(true);
                birch.setAddX(1664);
            }
        }
    }

    public void loadScene2(int indexScene) {
        if (indexScene == 0 || indexScene == 1) {
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(128);
            }
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(768);
            }
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(1024);
            }
        }
        else if (indexScene == 2) {
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(250);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(RuDragonGame.WIDTH + 510);
            }
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(1408);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(1664);
            }
        }
        else if (indexScene == 3) {
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(32);
            }
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(512);
            }
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(810);
            }
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(1600);
            }
        }
        else if (indexScene == 4) {
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(420);
            }
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(720);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(976);
            }
        }
        else if (indexScene == 5) {
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(64);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(320);
            }
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(1600);
            }
        }
        else if (indexScene == 6) {
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(128);
            }
            if (!rock5.isGo()) {
                rock5.setGo(true);
                rock5.setAddX(780);
            }
        }
        else if (indexScene == 7) {
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(270);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(RuDragonGame.WIDTH + 680);
            }
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(980);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(1205);
            }
        }
        else if (indexScene == 8) {
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(64);
            }
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(640);
            }
            if (!rock5.isGo()) {
                rock5.setGo(true);
                rock5.setAddX(1660);
            }
        }
        else if (indexScene == 9) {
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(12);
            }
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(270);
            }
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(555);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(830);
            }
        }
        else if (indexScene == 10) {
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(640);
            }
            if (!rock5.isGo()) {
                rock5.setGo(true);
                rock5.setAddX(896);
            }
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(1660);
            }
        }
        else if (indexScene == 11) {
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(320);
            }
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(1020);
            }
        }
        else if (indexScene == 12) {
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(180);
            }
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(440);
            }
            if (!rock5.isGo()) {
                rock5.setGo(true);
                rock5.setAddX(700);
            }
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(950);
            }
        }
        else if (indexScene == 13) {
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(200);
            }
            if (!casket.isGo()) {
                casket.setGo(true);
                casket.setAddX(RuDragonGame.WIDTH + 510);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(840);
            }
        }
        else if (indexScene == 14) {
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(120);
            }
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(820);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(1654);
            }
        }
        else if (indexScene == 15) {
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(64);
            }
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(880);
            }
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(1654);
            }
        }
        else if (indexScene == 16) {
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(880);
            }
        }
        else if (indexScene == 17) {
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(880);
            }
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(1130);
            }
        }
        else if (indexScene == 18) {
            if (!rock5.isGo()) {
                rock5.setGo(true);
                rock5.setAddX(880);
            }
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(1100);
            }
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(1340);
            }
        }
        else if (indexScene == 19) {
            if (!rock3.isGo()) {
                rock3.setGo(true);
                rock3.setAddX(350);
            }
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(530);
            }
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(720);
            }
            if (!rock5.isGo()) {
                rock5.setGo(true);
                rock5.setAddX(930);
            }
        }
        else if (indexScene == 20) {
            if (!rock1.isGo()) {
                rock1.setGo(true);
                rock1.setAddX(128);
            }
            if (!rock7.isGo()) {
                rock7.setGo(true);
                rock7.setAddX(368);
            }
            if (!rock6.isGo()) {
                rock6.setGo(true);
                rock6.setAddX(570);
            }
            if (!rock2.isGo()) {
                rock2.setGo(true);
                rock2.setAddX(790);
            }
            if (!rock4.isGo()) {
                rock4.setGo(true);
                rock4.setAddX(1000);
            }
        }
    }

    public void speedScene(float speed) {
        casket.setSpeed(speed);
        kine.setSpeed(speed);
        pig.setSpeed(speed);
        goat.setSpeed(speed);
        if (ash.isGo()) ash.setSpeed(speed);
        if (aspen.isGo()) aspen.setSpeed(speed);
        if (birch.isGo()) birch.setSpeed(speed);
        if (fir.isGo()) fir.setSpeed(speed);
        if (linden.isGo()) linden.setSpeed(speed);
        if (maple.isGo()) maple.setSpeed(speed);
        if (oak.isGo()) oak.setSpeed(speed);
        if (pine.isGo()) pine.setSpeed(speed);
        if (poplar.isGo()) poplar.setSpeed(speed);
        if (spruce.isGo()) spruce.setSpeed(speed);
        if (willow.isGo()) willow.setSpeed(speed);
        rock1.setSpeed(speed);
        rock2.setSpeed(speed);
        rock3.setSpeed(speed);
        rock4.setSpeed(speed);
        rock5.setSpeed(speed);
        rock6.setSpeed(speed);
        rock7.setSpeed(speed);
    }

    public void updateScene(float deltaTime) {
        casket.update(deltaTime);
        kine.update(deltaTime);
        pig.update(deltaTime);
        goat.update(deltaTime);
        if (ash.isGo()) ash.update(deltaTime);
        if (aspen.isGo()) aspen.update(deltaTime);
        if (birch.isGo()) birch.update(deltaTime);
        if (fir.isGo()) fir.update(deltaTime);
        if (linden.isGo()) linden.update(deltaTime);
        if (maple.isGo()) maple.update(deltaTime);
        if (oak.isGo()) oak.update(deltaTime);
        if (pine.isGo()) pine.update(deltaTime);
        if (poplar.isGo()) poplar.update(deltaTime);
        if (spruce.isGo()) spruce.update(deltaTime);
        if (willow.isGo()) willow.update(deltaTime);
        rock1.update(deltaTime);
        rock2.update(deltaTime);
        rock3.update(deltaTime);
        rock4.update(deltaTime);
        rock5.update(deltaTime);
        rock6.update(deltaTime);
        rock7.update(deltaTime);
    }

    public void renderScene(SpriteBatch batch) {
        kine.render(batch);
        pig.render(batch);
        goat.render(batch);
        if (ash.isGo()) ash.render(batch);
        if (aspen.isGo()) aspen.render(batch);
        if (birch.isGo()) birch.render(batch);
        if (fir.isGo()) fir.render(batch);
        if (linden.isGo()) linden.render(batch);
        if (maple.isGo()) maple.render(batch);
        if (oak.isGo()) oak.render(batch);
        if (pine.isGo()) pine.render(batch);
        if (poplar.isGo()) poplar.render(batch);
        if (spruce.isGo()) spruce.render(batch);
        if (willow.isGo()) willow.render(batch);
        casket.render(batch);
        rock1.render(batch);
        rock2.render(batch);
        rock3.render(batch);
        rock4.render(batch);
        rock5.render(batch);
        rock6.render(batch);
        rock7.render(batch);
    }

    /*** gets ***/

    public Casket getCasket() {
        return casket;
    }

    public Kine getKine() {
        return kine;
    }

    public Goat getGoat() {
        return goat;
    }

    public Pig getPig() {
        return pig;
    }

    public Ash getAsh() {
        return ash;
    }

    public Aspen getAspen() {
        return aspen;
    }

    public Birch getBirch() {
        return birch;
    }

    public Fir getFir() {
        return fir;
    }

    public Linden getLinden() {
        return linden;
    }

    public Maple getMaple() {
        return maple;
    }

    public Oak getOak() {
        return oak;
    }

    public Pine getPine() {
        return pine;
    }

    public Poplar getPoplar() {
        return poplar;
    }

    public Spruce getSpruce() {
        return spruce;
    }

    public Willow getWillow() {
        return willow;
    }

    public Rock1 getRock1() {
        return rock1;
    }

    public Rock2 getRock2() {
        return rock2;
    }

    public Rock3 getRock3() {
        return rock3;
    }

    public Rock4 getRock4() {
        return rock4;
    }

    public Rock5 getRock5() {
        return rock5;
    }

    public Rock6 getRock6() {
        return rock6;
    }

    public Rock7 getRock7() {
        return rock7;
    }
}