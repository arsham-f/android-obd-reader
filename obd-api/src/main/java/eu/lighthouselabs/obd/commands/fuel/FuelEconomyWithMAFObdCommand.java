/*
 * TODO put header 
 */
package eu.lighthouselabs.obd.commands.fuel;

import eu.lighthouselabs.obd.enums.AvailableCommandNames;
import eu.lighthouselabs.obd.enums.FuelType;

/**
 * TODO put description
 */
public class FuelEconomyWithMAFObdCommand {

	private int speed = 1;
	private double maf = 1;
	private float ltft = 1;
	private FuelType fuelType;
	private boolean useImperial = false;

	double mpg = -1;
	double litersPer100Km = -1;

	/**
	 * @param command
	 */
	public FuelEconomyWithMAFObdCommand(FuelType fuelType, int speed,
			double maf, float ltft, boolean useImperial) {
		this.fuelType = fuelType;
		this.speed = speed;
		this.maf = maf;
		this.ltft = ltft;
		this.useImperial = useImperial;

		mpg = (14.7 * (1 + ltft / 100) * 6.17 * 454 * speed * 0.621371)
				/ (3600 * maf);
		// mpg = 710.7 * speed / maf * (1 + ltft / 100);
		litersPer100Km = mpg / 2.2352;

		// float fuelDensity = 0.71f;
		// if (fuelType.equals(FuelType.DIESEL))
		// fuelDensity = 0.832f;
		// litersPer100Km = (maf / 14.7 / fuelDensity * 3600) * (1 + tft / 100)/
		// speed * 100;
	}

	/**
	 * As it's a fake command, neither do we need to send request or read
	 * response.
	 */
	public double getMPG() {
		return mpg;
	}

	/**
	 * @return the fuel consumption in l/100km
	 */
	public double getLitersPer100Km() {
		return litersPer100Km;
	}

	public String getFormattedResult() {
		String res = "NODATA";

		res = String.format("%.2f%s", litersPer100Km, "l/100km");

		if (useImperial)
			res = String.format("%.1f%s", mpg, "mpg");

		return res;
	}

	public String getName() {
		return AvailableCommandNames.FUEL_ECONOMY_WITH_MAF.getValue();
	}

}