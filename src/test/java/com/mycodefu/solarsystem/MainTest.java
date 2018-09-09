package com.mycodefu.solarsystem;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class MainTest extends ApplicationTest {
    private Group mainNode;

    @Before
    public void setUp () throws Exception {
    }

    @Override
    public void start (Stage stage) {
        Main main = new Main();
        this.mainNode = main.createMainNode(stage);
        Scene scene = main.createScene(mainNode);

        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void runTest() {
        clickOn(mainNode);
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}