package API;

import com.google.gson.Gson;
import models.MonAPI;
import models.Moneda;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Properties;

public class ExChangeRateAPI {
    private static String API_KEY;
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private static final String codes = "{\"result\":\"success\",\"supported_codes\":[[\"AED\",\"UAE Dirham\"],[\"AFN\",\"Afghan Afghani\"],[\"ALL\",\"Albanian Lek\"],[\"AMD\",\"Armenian Dram\"],[\"ANG\",\"Netherlands Antillian Guilder\"],[\"AOA\",\"Angolan Kwanza\"],[\"ARS\",\"Argentine Peso\"],[\"AUD\",\"Australian Dollar\"],[\"AWG\",\"Aruban Florin\"],[\"AZN\",\"Azerbaijani Manat\"],[\"BAM\",\"Bosnia and Herzegovina Convertible Mark\"],[\"BBD\",\"Barbados Dollar\"],[\"BDT\",\"Bangladeshi Taka\"],[\"BGN\",\"Bulgarian Lev\"],[\"BHD\",\"Bahraini Dinar\"],[\"BIF\",\"Burundian Franc\"],[\"BMD\",\"Bermudian Dollar\"],[\"BND\",\"Brunei Dollar\"],[\"BOB\",\"Bolivian Boliviano\"],[\"BRL\",\"Brazilian Real\"],[\"BSD\",\"Bahamian Dollar\"],[\"BTN\",\"Bhutanese Ngultrum\"],[\"BWP\",\"Botswana Pula\"],[\"BYN\",\"Belarusian Ruble\"],[\"BZD\",\"Belize Dollar\"],[\"CAD\",\"Canadian Dollar\"],[\"CDF\",\"Congolese Franc\"],[\"CHF\",\"Swiss Franc\"],[\"CLP\",\"Chilean Peso\"],[\"CNY\",\"Chinese Renminbi\"],[\"COP\",\"Colombian Peso\"],[\"CRC\",\"Costa Rican Colon\"],[\"CUP\",\"Cuban Peso\"],[\"CVE\",\"Cape Verdean Escudo\"],[\"CZK\",\"Czech Koruna\"],[\"DJF\",\"Djiboutian Franc\"],[\"DKK\",\"Danish Krone\"],[\"DOP\",\"Dominican Peso\"],[\"DZD\",\"Algerian Dinar\"],[\"EGP\",\"Egyptian Pound\"],[\"ERN\",\"Eritrean Nakfa\"],[\"ETB\",\"Ethiopian Birr\"],[\"EUR\",\"Euro\"],[\"FJD\",\"Fiji Dollar\"],[\"FKP\",\"Falkland Islands Pound\"],[\"FOK\",\"Faroese Kr\\u00f3na\"],[\"GBP\",\"Pound Sterling\"],[\"GEL\",\"Georgian Lari\"],[\"GGP\",\"Guernsey Pound\"],[\"GHS\",\"Ghanaian Cedi\"],[\"GIP\",\"Gibraltar Pound\"],[\"GMD\",\"Gambian Dalasi\"],[\"GNF\",\"Guinean Franc\"],[\"GTQ\",\"Guatemalan Quetzal\"],[\"GYD\",\"Guyanese Dollar\"],[\"HKD\",\"Hong Kong Dollar\"],[\"HNL\",\"Honduran Lempira\"],[\"HRK\",\"Croatian Kuna\"],[\"HTG\",\"Haitian Gourde\"],[\"HUF\",\"Hungarian Forint\"],[\"IDR\",\"Indonesian Rupiah\"],[\"ILS\",\"Israeli New Shekel\"],[\"IMP\",\"Manx Pound\"],[\"INR\",\"Indian Rupee\"],[\"IQD\",\"Iraqi Dinar\"],[\"IRR\",\"Iranian Rial\"],[\"ISK\",\"Icelandic Kr\\u00f3na\"],[\"JEP\",\"Jersey Pound\"],[\"JMD\",\"Jamaican Dollar\"],[\"JOD\",\"Jordanian Dinar\"],[\"JPY\",\"Japanese Yen\"],[\"KES\",\"Kenyan Shilling\"],[\"KGS\",\"Kyrgyzstani Som\"],[\"KHR\",\"Cambodian Riel\"],[\"KID\",\"Kiribati Dollar\"],[\"KMF\",\"Comorian Franc\"],[\"KRW\",\"South Korean Won\"],[\"KWD\",\"Kuwaiti Dinar\"],[\"KYD\",\"Cayman Islands Dollar\"],[\"KZT\",\"Kazakhstani Tenge\"],[\"LAK\",\"Lao Kip\"],[\"LBP\",\"Lebanese Pound\"],[\"LKR\",\"Sri Lanka Rupee\"],[\"LRD\",\"Liberian Dollar\"],[\"LSL\",\"Lesotho Loti\"],[\"LYD\",\"Libyan Dinar\"],[\"MAD\",\"Moroccan Dirham\"],[\"MDL\",\"Moldovan Leu\"],[\"MGA\",\"Malagasy Ariary\"],[\"MKD\",\"Macedonian Denar\"],[\"MMK\",\"Burmese Kyat\"],[\"MNT\",\"Mongolian T\\u00f6gr\\u00f6g\"],[\"MOP\",\"Macanese Pataca\"],[\"MRU\",\"Mauritanian Ouguiya\"],[\"MUR\",\"Mauritian Rupee\"],[\"MVR\",\"Maldivian Rufiyaa\"],[\"MWK\",\"Malawian Kwacha\"],[\"MXN\",\"Mexican Peso\"],[\"MYR\",\"Malaysian Ringgit\"],[\"MZN\",\"Mozambican Metical\"],[\"NAD\",\"Namibian Dollar\"],[\"NGN\",\"Nigerian Naira\"],[\"NIO\",\"Nicaraguan C\\u00f3rdoba\"],[\"NOK\",\"Norwegian Krone\"],[\"NPR\",\"Nepalese Rupee\"],[\"NZD\",\"New Zealand Dollar\"],[\"OMR\",\"Omani Rial\"],[\"PAB\",\"Panamanian Balboa\"],[\"PEN\",\"Peruvian Sol\"],[\"PGK\",\"Papua New Guinean Kina\"],[\"PHP\",\"Philippine Peso\"],[\"PKR\",\"Pakistani Rupee\"],[\"PLN\",\"Polish Z\\u0142oty\"],[\"PYG\",\"Paraguayan Guaran\\u00ed\"],[\"QAR\",\"Qatari Riyal\"],[\"RON\",\"Romanian Leu\"],[\"RSD\",\"Serbian Dinar\"],[\"RUB\",\"Russian Ruble\"],[\"RWF\",\"Rwandan Franc\"],[\"SAR\",\"Saudi Riyal\"],[\"SBD\",\"Solomon Islands Dollar\"],[\"SCR\",\"Seychellois Rupee\"],[\"SDG\",\"Sudanese Pound\"],[\"SEK\",\"Swedish Krona\"],[\"SGD\",\"Singapore Dollar\"],[\"SHP\",\"Saint Helena Pound\"],[\"SLE\",\"Sierra Leonean Leone\"],[\"SLL\",\"Sierra Leonean Leone\"],[\"SOS\",\"Somali Shilling\"],[\"SRD\",\"Surinamese Dollar\"],[\"SSP\",\"South Sudanese Pound\"],[\"STN\",\"S\\u00e3o Tom\\u00e9 and Pr\\u00edncipe Dobra\"],[\"SYP\",\"Syrian Pound\"],[\"SZL\",\"Eswatini Lilangeni\"],[\"THB\",\"Thai Baht\"],[\"TJS\",\"Tajikistani Somoni\"],[\"TMT\",\"Turkmenistan Manat\"],[\"TND\",\"Tunisian Dinar\"],[\"TOP\",\"Tongan Pa\\u02bbanga\"],[\"TRY\",\"Turkish Lira\"],[\"TTD\",\"Trinidad and Tobago Dollar\"],[\"TVD\",\"Tuvaluan Dollar\"],[\"TWD\",\"New Taiwan Dollar\"],[\"TZS\",\"Tanzanian Shilling\"],[\"UAH\",\"Ukrainian Hryvnia\"],[\"UGX\",\"Ugandan Shilling\"],[\"USD\",\"United States Dollar\"],[\"UYU\",\"Uruguayan Peso\"],[\"UZS\",\"Uzbekistani So'm\"],[\"VES\",\"Venezuelan Bol\\u00edvar Soberano\"],[\"VND\",\"Vietnamese \\u0110\\u1ed3ng\"],[\"VUV\",\"Vanuatu Vatu\"],[\"WST\",\"Samoan T\\u0101l\\u0101\"],[\"XAF\",\"Central African CFA Franc\"],[\"XCD\",\"East Caribbean Dollar\"],[\"XDR\",\"Special Drawing Rights\"],[\"XOF\",\"West African CFA franc\"],[\"XPF\",\"CFP Franc\"],[\"YER\",\"Yemeni Rial\"],[\"ZAR\",\"South African Rand\"],[\"ZMW\",\"Zambian Kwacha\"],[\"ZWL\",\"Zimbabwean Dollar\"]]}\n";
    public static void apKey() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")) {
            properties.load(fis);
            API_KEY = properties.getProperty("API_KEY");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void conect(String fromCurrency, String toCurrency, Double mount) {
        apKey();
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency + "/" + mount))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            MonAPI monAPI = new Gson().fromJson(responseBody, MonAPI.class);
            double conversionResults = monAPI.conversion_result();
            System.out.println("La moneda de "+fromCurrency+" a "+toCurrency+" es "+mount+" "+fromCurrency+" = "+conversionResults+" "+toCurrency);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }
    public static List<List<String>> codeListString() {
        Moneda moneda = new Gson().fromJson(codes, Moneda.class);
        return moneda.supported_codes();
    }

    public static void convertCodes(int codeFromCurrency, int codeToCurrency, double mount){
        List<List<String>> supportedCodes = codeListString();
        List<String> fromCurrency = supportedCodes.get(codeFromCurrency-1);
        List<String> toCurrency = supportedCodes.get(codeToCurrency-1);
        conect(fromCurrency.get(0), toCurrency.get(0), mount);
    }
}