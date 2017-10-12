# Australia Post - Postage Assessment Calculator Java SDK

[![Build Status](https://travis-ci.org/philBrown/auspost-pac.svg?branch=master)](https://travis-ci.org/philBrown/auspost-pac)

Java SDK to access the AusPost postage assessment calculator REST API.

Uses Spring `RestTemplate` to access the XML resources. Would have used the JSON resources but AusPost doesn't know how
to convert XML to JSON without messing up the arrays.

See API reference - https://developers.auspost.com.au/apis/pac/getting-started.
