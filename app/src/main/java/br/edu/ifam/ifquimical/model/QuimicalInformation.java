package br.edu.ifam.ifquimical.model;

public class QuimicalInformation implements Comparable {

    private String name;
    private String formula;
    private String firstAidActions;
    private String fireSafety;
    private String handlingAndStorage;
    private String exposureControlAndPersonalProtection;
    private String spillOrLeak;
    private String stabilityAndReactivity;

    public QuimicalInformation() {
    }

    public QuimicalInformation(String name, String formula, String firstAidActions, String fireSafety, String handlingAndStorage, String exposureControlAndPersonalProtection, String spillOrLeak, String stabilityAndReactivity) {
        this.name = name;
        this.formula = formula;
        this.firstAidActions = firstAidActions;
        this.fireSafety = fireSafety;
        this.handlingAndStorage = handlingAndStorage;
        this.exposureControlAndPersonalProtection = exposureControlAndPersonalProtection;
        this.spillOrLeak = spillOrLeak;
        this.stabilityAndReactivity = stabilityAndReactivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFirstAidActions() {
        return firstAidActions;
    }

    public void setFirstAidActions(String firstAidActions) {
        this.firstAidActions = firstAidActions;
    }

    public String getFireSafety() {
        return fireSafety;
    }

    public void setFireSafety(String fireSafety) {
        this.fireSafety = fireSafety;
    }

    public String getHandlingAndStorage() {
        return handlingAndStorage;
    }

    public void setHandlingAndStorage(String handlingAndStorage) {
        this.handlingAndStorage = handlingAndStorage;
    }

    public String getExposureControlAndPersonalProtection() {
        return exposureControlAndPersonalProtection;
    }

    public void setExposureControlAndPersonalProtection(String exposureControlAndPersonalProtection) {
        this.exposureControlAndPersonalProtection = exposureControlAndPersonalProtection;
    }

    public String getSpillOrLeak() {
        return spillOrLeak;
    }

    public void setSpillOrLeak(String spillOrLeak) {
        this.spillOrLeak = spillOrLeak;
    }

    public String getStabilityAndReactivity() {
        return stabilityAndReactivity;
    }

    public void setStabilityAndReactivity(String stabilityAndReactivity) {
        this.stabilityAndReactivity = stabilityAndReactivity;
    }

    @Override
    public int compareTo(Object o) {
        QuimicalInformation qiOther = (QuimicalInformation) o;
        return getName().compareTo(qiOther.getName());
    }
}
