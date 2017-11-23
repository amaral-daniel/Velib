package Simulation;

import Data.Result;
import Data.Scenario Resume;
import Data.Station;
import Data.Trip;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("458f34b7-cc9d-41e9-a830-5211ea597923")
public class Scenario {
    @objid ("c1ad2d97-e7ec-4e8a-aa19-f60219c444ca")
    private boolean regulation;

    @objid ("384d4d84-0c8e-41cf-9a99-9c0fc2a9a0f4")
    private float collaborationRate;

    @objid ("a3d625b4-c2b0-4beb-8d60-17c3a8a97359")
    private float growthParameter;

    @objid ("5eebbdbf-6cfa-44d4-9645-5e9649cf1082")
    public Trip trip;

    @objid ("82a9089a-131f-4e0c-b982-8bca7bd87f2b")
    public Station station;

    @objid ("a7965491-a9ad-4376-b287-6c1711ebad0b")
    public Scenario Resume scenario Resume;

    @objid ("ca2b6870-101c-402d-a43d-6e7ad9b37f6b")
    public Result result;

    @objid ("f01db4d9-90db-4bda-94db-61f6485dda6d")
    public void runTrips() {
    }

    @objid ("74708208-9372-4c59-9ef0-e11fb9faeffb")
    public void noRegulation() {
    }

    @objid ("1bc0983b-93f7-4430-a171-4e92a3d9dcf9")
    public void newBehaviour() {
    }

    @objid ("ab774896-c3de-4638-b206-23ac13acb232")
    public void growPopularity() {
    }

}
