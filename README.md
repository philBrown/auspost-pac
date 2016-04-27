# Australia Post - Postage Assessment Calculator Java SDK

Java SDK to access the AusPost postage assessment calculator REST API.

Uses Spring `RestTemplate` to access the XML resources. Would have used the JSON resources but AusPost doesn't know how
to convert XML to JSON without messing up the arrays.

See API reference - https://developers.auspost.com.au/apis/pac/getting-started.
