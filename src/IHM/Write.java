package IHM;

import Simulation.Scenario;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8208822d-876b-49c2-bbe2-a528d90560a8")
public class Write {
    @objid ("9f965698-b7b0-4aeb-a14c-ff03447d579e")
    public class Interface {
        @objid ("6d54e82e-a53d-432e-939d-a9beeec50b3a")
        public Scenario scenario;

        @objid ("b5207468-39be-4e81-8c7d-53b5832cbf1b")
        public void displayScenario() {
        }

        @objid ("1d07c974-ae11-4979-8f2a-6ea7a6315eb3")
        public void askUser(final int simulationCase) {
        }

    }

}
