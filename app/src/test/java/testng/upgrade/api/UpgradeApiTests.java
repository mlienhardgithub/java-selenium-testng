package testng.upgrade.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import testng.upgrade.utils.Utilities;

public class UpgradeApiTests {
	String apiUrl;
	
	@BeforeClass
    public void beforeClass() throws IOException {
    	final Properties configProps = Utilities.loadConfigProperties();
		apiUrl = configProps.getProperty("api.url.test");
    }
	
	@Test(description="Should find an existing loan")
	public void testFindExistingLoan() {
		final Map<String, Object> map = new HashMap<>();
		map.put("loanAppUuid", "b8096ec7-2150-405f-84f5-ae99864b3e96");
		map.put("skipSideEffects", true);
		
		final ValidatableResponse response =
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.headers("Content-Type", "application/json",
			"x-cf-source-id", "coding-challenge",
			"x-cf-corr-id", "cc23b318-156e-473f-aa6e-517bf091a0f0")
			.body(map)
		.when()
			.post(apiUrl)
		.then()
			.assertThat()
			.statusCode(200)
			.body("loanAppResumptionInfo.loanAppId", equalTo(105351767))
			.body("loanAppResumptionInfo.loanAppUuid", equalTo("2bc53d36-a4fb-46fd-92db-37fd7ddfeb1d"))
			.body("loanAppResumptionInfo.referrer", equalTo("LENDING_TREE"))
			.body("loanAppResumptionInfo.status", equalTo("EXPIRED"))
			.body("loanAppResumptionInfo.productType", equalTo("PERSONAL_LOAN"))
			.body("loanAppResumptionInfo.sourceSystem", equalTo("PARTNER_FUNNEL_V2"))
			.body("loanAppResumptionInfo.desiredAmount", equalTo(25000.00F))
			.body("loanAppResumptionInfo.borrowerResumptionInfo.firstName", equalTo("Benjamin"))
			.body("loanAppResumptionInfo.borrowerResumptionInfo.maskedEmail", equalTo("q********6@upgrade.com"))
			.body("loanAppResumptionInfo.borrowerResumptionInfo.userUuid", equalTo(null))
			.body("loanAppResumptionInfo.borrowerResumptionInfo.ssnRequired", equalTo(true))
			.body("loanAppResumptionInfo.borrowerResumptionInfo.state", equalTo("OH"))
			.body("loanAppResumptionInfo.coBorrowerResumptionInfo", equalTo(null))
			.body("loanAppResumptionInfo.turnDown", equalTo(false))
			.body("loanAppResumptionInfo.hasLogin", equalTo(false))
			.body("loanAppResumptionInfo.availableAppImprovements", equalTo(null))
			.body("loanAppResumptionInfo.cashOutAmount", equalTo(null))
			.body("loanAppResumptionInfo.canAddCollateral", equalTo(false))
			.body("loanAppResumptionInfo.programDefinitionCode", equalTo("PL_DEFAULT"))
			.body("loanAppResumptionInfo.rewardProgramId", equalTo(null))
			.body("loanAppResumptionInfo.addon", equalTo(null))
			.body("loanAppResumptionInfo.isMobileDiscountApplied", equalTo(null))
			.body("loanAppResumptionInfo.checkingDiscountAvailable", equalTo(false))
			.body("loanAppResumptionInfo.plDepositBundleEligible", equalTo(false))
			.body("loanAppResumptionInfo.promotions", hasSize(0))
			.body("loanAppResumptionInfo.loanApplicationBundle.uuid", equalTo("ca980d9a-b85c-4922-83bb-ef477518ba8b"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions", hasSize(1))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].uuid", equalTo("19824179-b180-4ef0-9a1f-a7501a908278"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].loanAppBundleUuid", equalTo("ca980d9a-b85c-4922-83bb-ef477518ba8b"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].promotionProgramCode", equalTo("WB_PL_RC_01"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].promotionAttributionType", equalTo("WELCOME_BONUS_BUNDLE_PL_DEPOSIT"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].promotionLinkCode", equalTo("25860fd5-9c39-45d9-85bc-2c6782192738"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].enrollStatus", equalTo("OFFERED"))
			.body("loanAppResumptionInfo.loanApplicationBundle.promotions[0].updatedByCrossSell", equalTo(null))
			.body("loanAppResumptionInfo.resumptionAdditionalInfo.cardIssuingBank", equalTo(null))
			.body("loanAppResumptionInfo.creditUnionDisclaimers.showDisclaimers", equalTo(true))
			.body("loanAppResumptionInfo.employmentRequired", equalTo(true))
			.body("offers", hasSize(0))
			.body("selectedOffer", equalTo(null))
			.body("requiredAgreements", hasSize(0))
			.body("resetOptions", hasItems("LEAD_SECRET", "WIPE", "DEACTIVATE_USER"))
			.body("originalLoanApp", equalTo(null))
			.log().all()
		;
	}
	
	@Test(description="Should not find a loan")
	public void testInvalidLoan( ) {
		final Map<String, Object> map = new HashMap<>();
		map.put("loanAppUuid", "cc23b318-156e-473f-aa6e-517bf091a0f0");
		map.put("skipSideEffects", true);
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.headers("Content-Type", "application/json",
			"x-cf-source-id", "coding-challenge",
			"x-cf-corr-id", "cc23b318-156e-473f-aa6e-517bf091a0f0")
			.body(map)
		.when()
			.post(apiUrl)
		.then()
			.assertThat()
			.statusCode(404)
			.body("httpStatus", equalTo("NOT_FOUND"))
			.body("code", equalTo(100001))
			.body("codeName", equalTo("MISSING_LOAN_APPLICATION"))
			.body("message", equalTo("Loan application does not exist."))
			.log().all()
		;
	}
}
