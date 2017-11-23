package Data;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.apache.commons.lang.enum.Enum;

@objid ("149dc629-31d7-4b6d-9257-57fe14c62a0b")
public class Trip {
    @objid ("f713f273-296d-4bbe-9dda-6d012b0ec1ad")
    private Enum reason;

    @objid ("012ee248-869f-462f-a11c-edc4465e3c7b")
    private Date startDate;

    @objid ("8551c355-7f99-42ac-be37-079730c6591b")
    private Date endDate;

    @objid ("fcf592e5-1020-4fdc-bc0f-24d0f0cd7e5c")
    private boolean isValid;

    @objid ("c0726cd4-8cc6-4125-b99a-9e4a17b6bd43")
    public Station station;

    @objid ("a25bf2b2-ac4c-4c06-b4b4-bded5b2e5dee")
    public void getters(String p1) {
    }

    @objid ("9b782f30-557a-412d-8d01-067894227bec")
    public void setters(final String p1) {
    }

    @objid ("6638720a-6000-460d-a7ca-44ddc4004432")
    public void startTrip() {
    }

    @objid ("660187de-85f6-4215-b3eb-eba45c837d7a")
    public void endTrip() {
    }

}
