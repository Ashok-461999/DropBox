package com.cloudeagle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DropboxTeamInfo {

    private static final String ACCESS_TOKEN = "sl.u.AGGN90GX4l0C66b61XvTYp67gCGouGexN4UXpIKGH8b3E-3GBaNltgmi_bhIltVGta0B9phxBvY5uu6OKg-zPBxjDMxrSCMwiSiSbhwULfsmHcWAKIfgc5HiE9iRA3zryVZW4Nwc0jZBabbcjr5lke42qsrprvwei5ALJJRt-rkmeT1HOUAAP3Rq_ErYdYx_Ig0RgY_ZrY8VaLX-n0sQCfOsLvbukAHgEToVQ3eD0roge-hlbSQg1-_IVgIElm_QO5dSVWunMW-emCzhkFkCX68UwlcGG79yHMjaqsXdV8WzeTZvNAxmeR1oVKIviL-xcazDV_ipPaNKrTQP6eSYtFAWIXVp78_HmZubV_FIhIhlv2NGrkOLmgvgYI-lSVH8K23blx-Uic6pO73pAAuo07t3-biYG4WFHD9dMC6votfBDfYIlmBH0_5rLEDdtX9oeDo20B3xyce2aHfQTsJaHxh9LfZpXqCMETRiMyIOxmR1BeIW_xqDVx8rWG9XNGO4PCcVBHziw3xE0DlM_RZeFXxq7wJk1ZA2oQIzDM-Uqs8gSyFs7G8kUmPkLtAHIhsLHVyEB0R1vS4I8ckP-j7edDCRdP0w3FXBfOTd-qE7-jauhjisQ2DLnPAKKLPGjstsDWD_nDx2mHPIadkp2lhjcgP_ACHVhXuayVwU_2dYt9A5g63PW4UCJV9pt0bpxs1qnDLS5qKaFyG4cWIWTgtunNpuEykrUG9-erhx7ae7OYwX93LI376ebmeEmZnYMW9QaXByoCm54ETKfIMLSkxeAx32_Y6Ix2slvNJxu8M_qNiLRDqKomLgz-Lec3Q9nnVnjKLmS5S3BIUGCXjSex-xaSq-1pg7CEJDn6UrtRwEk3SSihtmyoySE0i_BTurM-0kilcF_cENdziGOKQBBu2r6yJ04YrnQX-EWZX5Wo0XNgorfDz3FZuL8ncnFvz9lHqHARemtD4IvP6cX5S85OqsUnSfxCFuincoh_n8e-iGxXe94F90WtHWiw7MzQK-MYodrRLgQZIvdX5sz-lsO-HcmLe3sLH3EZGdR7uG7TXDpBozwqj__YgIAEe3oLTj04ix35ItSDM4W3y_wv2O3Z0n2unu-JcM1hPRnVH5UOZvgYTzZw"
;
    private static final String TEAM_INFO_URL = "https://api.dropboxapi.com/2/team/get_info";


    public static void main(String[] args) {
        DropboxTeamInfo dropboxClient = new DropboxTeamInfo();
        dropboxClient.fetchTeamInfo();
    }

    public void fetchTeamInfo() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEAM_INFO_URL))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body:\n" + responseBody);

            if (statusCode != 200) {
               System.err.println(" Failed to fetch team info. Status: " + statusCode);
            }

        } catch (IOException e) {
            System.err.println(" I/O error while calling Dropbox API: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Request was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); 
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}