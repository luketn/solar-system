package com.mycodefu.solarsystem;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private double width;

    public void start(Stage primaryStage) {
        Group mainNode = createMainNode(primaryStage);
        Scene scene = createScene(mainNode);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Scene createScene(Group mainNode) {
        ImagePattern background = new ImagePattern(getImageFromResource("/sky.png"));
        return new Scene(mainNode, background);
    }

    Group createMainNode(Stage primaryStage) {
        initStage(primaryStage);

        Sphere sun = createSun(primaryStage);
        Sphere earth = createEarth(primaryStage);
        return new Group(earth, sun);
    }

    private Image getImageFromResource(String resourcePath) {
        return new Image(this.getClass().getResourceAsStream(resourcePath));
    }

    private Sphere createEarth(Stage primaryStage) {
        Sphere earth = new Sphere(relativeSize(100));
        earth.setLayoutX(primaryStage.getWidth() / 2 + relativeSize(150));
        earth.setLayoutY(primaryStage.getHeight() / 2 + relativeSize(150));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(getImageFromResource("/earthmap1k.jpg"));
        earth.setMaterial(material);

        rotateEarth(earth);

        return earth;
    }

    private Sphere createSun(Stage primaryStage) {
        Sphere sun = new Sphere(relativeSize(25));
        sun.setLayoutX(primaryStage.getWidth() / 2);
        sun.setLayoutY(primaryStage.getHeight() / 2);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(getImageFromResource("/sunmap.jpg"));
        material.setSpecularColor(Color.WHITE);
        material.setSpecularPower(1);
        sun.setMaterial(material);
        return sun;
    }

    private void initStage(Stage primaryStage) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        this.width = primaryStage.getWidth();
    }

    private double relativeSize(double value) {
        return value * (1440d / width);
    }

    private void rotateEarth(Sphere earth) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(earth);
        rotateTransition.setDuration(Duration.seconds(15));
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
    }
}
