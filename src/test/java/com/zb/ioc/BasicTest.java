package com.zb.ioc;

import com.zb.ioc.annotation.Autowired;
import com.zb.ioc.annotation.Component;
import com.zb.ioc.annotation.Qualifier;
import com.zb.ioc.utils.Digraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@Component
public class BasicTest {
    @Autowired
    private Song song;

    private Song anotherSong;
    private Singer anothersinger;

    public Song getSong() {
        return song;
    }

    public Song getAnotherSong() {
        return anotherSong;
    }

    @Autowired
    private void setAnotherSong(Song song, Singer singer) {
        anotherSong = song;
        anothersinger = singer;
    }

    private static Map<Class, Object> maps;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Bootstrap bootstrap = new Bootstrap("com.zb.ioc");
        maps =  bootstrap.createBeanMap();
    }
    @Test
    public void test001() {
        BasicTest basicTest = (BasicTest)maps.get(BasicTest.class);
        assertEquals(basicTest.getSong().getName(), "Chinese GenreChinese");
    }

    @Test
    public void test002() {
        Digraph<Integer> digraph = new Digraph<>();
        digraph.addEdge(5, 11);
        digraph.addEdge(7, 11);
        digraph.addEdge(7, 8);
        digraph.addEdge(3, 8);
        digraph.addEdge(3, 10);
        digraph.addEdge(11, 2);
        digraph.addEdge(11, 9);
        digraph.addEdge(11, 10);
        digraph.addEdge(8, 9);
        for (Integer i :
                digraph.getTopologicalList()) {
            System.out.println(i);
        }
        assertEquals(true, true);
    }

    @Test
    public void test003() {
        Digraph<Integer> digraph = new Digraph<>();
        digraph.addEdge(5, 11);
        digraph.addEdge(7, 11);
        digraph.addEdge(7, 8);
        digraph.addEdge(3, 8);
        digraph.addEdge(3, 10);
        digraph.addEdge(11, 2);
        digraph.addEdge(11, 9);
        digraph.addEdge(11, 10);
        digraph.addEdge(8, 9);
        digraph.addEdge(9, 10);
        digraph.addEdge(10, 3);
        digraph.getTopologicalList();
        assertEquals(digraph.hasErrors(), true);
    }

    @Test
    public void test004() {
        BasicTest basicTest = (BasicTest)maps.get(BasicTest.class);
        assertEquals(basicTest.getAnotherSong().getName() + " " + basicTest.anothersinger.name(),
                "Chinese GenreChinese Jay Chou");
    }

    @Test
    public void test005() {
        ChineseSong chineseSong = (ChineseSong)maps.get(ChineseSong.class);
        assertEquals(chineseSong.getSinger().name(),
                "Jay Chou");
    }

    @Autowired
    @Qualifier("American")
    private Song americanSong;

    @Test
    public void test006() {
        BasicTest basicTest = (BasicTest)maps.get(BasicTest.class);
        assertEquals(basicTest.americanSong.getName(),
                "American Song");
    }

    @Test
    public void test007() {
        BasicTest basicTest = (BasicTest)maps.get(BasicTest.class);
        assertEquals(((AmericanSong)basicTest.americanSong).getGenre().getName(),
                "County Genre");
    }

    @Test
    public void test008() {
        BasicTest basicTest = (BasicTest)maps.get(BasicTest.class);
        assertEquals(((AmericanSong)basicTest.americanSong).getSinger().name(),
                "Taylor Swift");
    }
}
