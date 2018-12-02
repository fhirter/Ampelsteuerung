public class DrivewayController implements Observer{

    private DrivewayModel drivewayModel;

    public DrivewayController(DrivewayModel drivewayModel) {
        setModel(drivewayModel);

    }



    @Override
    public void update() {

        drivewayModel.getPedestrianStripes();
        drivewayModel.getBicyclePatch();
        drivewayModel.getPublicTrafficRail();
    }

    private void setModel(DrivewayModel drivewayModel) {
        this.drivewayModel = drivewayModel;

    }

}
