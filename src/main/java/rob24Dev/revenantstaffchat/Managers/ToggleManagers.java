package rob24Dev.revenantstaffchat.Managers;

public class ToggleManagers {
    public boolean staffchattoggle;
    public boolean helperchattoggle;
    public boolean developerchattoggle;
    public boolean builderchattoggle;

    public ToggleManagers(Boolean staffchattoggle, Boolean helperchattoggle, Boolean developerchattoggle, Boolean builderchattoggle) {
        this.staffchattoggle = staffchattoggle;
        this.helperchattoggle = helperchattoggle;
        this.developerchattoggle = developerchattoggle;
        this.builderchattoggle = builderchattoggle;
    }
    public boolean isStaffchattoggle() {
        return staffchattoggle;
    }

    public void setStaffchattoggle(boolean staffchattoggle) {
        this.staffchattoggle = staffchattoggle;
    }

    public boolean isHelperchattoggle() {
        return helperchattoggle;
    }

    public void setHelperchattoggle(boolean helperchattoggle) {
        this.helperchattoggle = helperchattoggle;
    }

    public boolean isDeveloperchattoggle() {
        return developerchattoggle;
    }

    public void setDeveloperchattoggle(boolean developerchattoggle) {
        this.developerchattoggle = developerchattoggle;
    }

    public boolean isBuilderchattoggle() {
        return builderchattoggle;
    }

    public void setBuilderchattoggle(boolean builderchattoggle) {
        this.builderchattoggle = builderchattoggle;
    }

}
