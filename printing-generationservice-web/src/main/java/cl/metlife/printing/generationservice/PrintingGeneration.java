package cl.metlife.printing.generationservice;

import cl.metlife.printing.generation.GenerationServiceManager;
import org.apache.commons.codec.binary.Base64;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by BluePrints Developer on 11-11-2016.
 */
@WebService
public class PrintingGeneration {

    @EJB
    GenerationServiceManager generationServiceManager;

    @WebMethod
    public byte[] newPrint(@WebParam(name = "documentId") Long documentId){
        return generationServiceManager.createNewPrint(documentId);
    }

    @WebMethod
    public byte[] getPrint(@WebParam(name = "documentId")Long documentId,@WebParam(name = "printNumber") Long printNumber){
        return generationServiceManager.getPrint(documentId,printNumber);
    }

    private String getDummyPdf(){
        return  "JVBERi0xLjUNCiW1tbW1DQoxIDAgb2JqDQo8PC9UeXBlL0NhdGFsb2cvUGFnZXMgMiAwIFIvTGFu\n" +
                "Zyhlcy1DTCkgL1N0cnVjdFRyZWVSb290IDEzIDAgUi9NYXJrSW5mbzw8L01hcmtlZCB0cnVlPj4+\n" +
                "Pg0KZW5kb2JqDQoyIDAgb2JqDQo8PC9UeXBlL1BhZ2VzL0NvdW50IDEvS2lkc1sgMyAwIFJdID4+\n" +
                "DQplbmRvYmoNCjMgMCBvYmoNCjw8L1R5cGUvUGFnZS9QYXJlbnQgMiAwIFIvUmVzb3VyY2VzPDwv\n" +
                "Rm9udDw8L0YxIDUgMCBSPj4vRXh0R1N0YXRlPDwvR1MxMCAxMCAwIFIvR1MxMSAxMSAwIFI+Pi9Q\n" +
                "cm9jU2V0Wy9QREYvVGV4dC9JbWFnZUIvSW1hZ2VDL0ltYWdlSV0gPj4vTWVkaWFCb3hbIDAgMCA2\n" +
                "MTIgNzkyXSAvQ29udGVudHMgNCAwIFIvR3JvdXA8PC9UeXBlL0dyb3VwL1MvVHJhbnNwYXJlbmN5\n" +
                "L0NTL0RldmljZVJHQj4+L1RhYnMvUy9TdHJ1Y3RQYXJlbnRzIDA+Pg0KZW5kb2JqDQo0IDAgb2Jq\n" +
                "DQo8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDE4Nj4+DQpzdHJlYW0NCnicvdE9C8JADAbg\n" +
                "/eD+wzs7pMlZrx6UDv2wKAiKBw7iqJ0U1P8PXtVBhJusZggkhDyEIFkhz5NlNa/BRYGyrnDRion7\n" +
                "cGLAsCFnzuB60Go7wlmr0muVzAQixCn8USsJcwzBdEISWhk7GsOfwli7EUZ3CzvRPUt5la1Wu7xn\n" +
                "ij38QqsmbF1r9Q3vmKx55x/obyhLkv6HEhayNkoZNyRlyZg4ZQakhMlFJakHlIxQFv/Ux01olhXu\n" +
                "Xe6TZA0KZW5kc3RyZWFtDQplbmRvYmoNCjUgMCBvYmoNCjw8L1R5cGUvRm9udC9TdWJ0eXBlL1R5\n" +
                "cGUwL0Jhc2VGb250L0FCQ0RFRStNVC1FeHRyYS9FbmNvZGluZy9JZGVudGl0eS1IL0Rlc2NlbmRh\n" +
                "bnRGb250cyA2IDAgUi9Ub1VuaWNvZGUgMjAgMCBSPj4NCmVuZG9iag0KNiAwIG9iag0KWyA3IDAg\n" +
                "Ul0gDQplbmRvYmoNCjcgMCBvYmoNCjw8L0Jhc2VGb250L0FCQ0RFRStNVC1FeHRyYS9TdWJ0eXBl\n" +
                "L0NJREZvbnRUeXBlMi9UeXBlL0ZvbnQvQ0lEVG9HSURNYXAvSWRlbnRpdHkvRFcgMTAwMC9DSURT\n" +
                "eXN0ZW1JbmZvIDggMCBSL0ZvbnREZXNjcmlwdG9yIDkgMCBSL1cgMjIgMCBSPj4NCmVuZG9iag0K\n" +
                "OCAwIG9iag0KPDwvT3JkZXJpbmcoSWRlbnRpdHkpIC9SZWdpc3RyeShBZG9iZSkgL1N1cHBsZW1l\n" +
                "bnQgMD4+DQplbmRvYmoNCjkgMCBvYmoNCjw8L1R5cGUvRm9udERlc2NyaXB0b3IvRm9udE5hbWUv\n" +
                "QUJDREVFK01ULUV4dHJhL0ZsYWdzIDMyL0l0YWxpY0FuZ2xlIDAvQXNjZW50IDc5OC9EZXNjZW50\n" +
                "IC0yMTQvQ2FwSGVpZ2h0IDgwMC9BdmdXaWR0aCA2MzAvTWF4V2lkdGggMTAyMS9Gb250V2VpZ2h0\n" +
                "IDQwMC9YSGVpZ2h0IDI1MC9MZWFkaW5nIDI4Ni9TdGVtViA2My9Gb250QkJveFsgLTEgLTIxNCAx\n" +
                "MDIwIDgwMF0gL0ZvbnRGaWxlMiAyMSAwIFI+Pg0KZW5kb2JqDQoxMCAwIG9iag0KPDwvVHlwZS9F\n" +
                "eHRHU3RhdGUvQk0vTm9ybWFsL2NhIDE+Pg0KZW5kb2JqDQoxMSAwIG9iag0KPDwvVHlwZS9FeHRH\n" +
                "U3RhdGUvQk0vTm9ybWFsL0NBIDE+Pg0KZW5kb2JqDQoxMiAwIG9iag0KPDwvQXV0aG9yKEFndXN0\n" +
                "aW4gU2FudGlhZ28gR2FsbGFyZG8pIC9DcmVhdG9yKP7/AE0AaQBjAHIAbwBzAG8AZgB0AK4AIABX\n" +
                "AG8AcgBkACAAMgAwADEANikgL0NyZWF0aW9uRGF0ZShEOjIwMTgwMTA4MTYyNTQ0LTAzJzAwJykg\n" +
                "L01vZERhdGUoRDoyMDE4MDEwODE2MjU0NC0wMycwMCcpIC9Qcm9kdWNlcij+/wBNAGkAYwByAG8A\n" +
                "cwBvAGYAdACuACAAVwBvAHIAZAAgADIAMAAxADYpID4+DQplbmRvYmoNCjE5IDAgb2JqDQo8PC9U\n" +
                "eXBlL09ialN0bS9OIDYvRmlyc3QgMzkvRmlsdGVyL0ZsYXRlRGVjb2RlL0xlbmd0aCAyODY+Pg0K\n" +
                "c3RyZWFtDQp4nG1RTW/CMAy9I/Ef/A/c8LVOQkjTAG1CoKpF2gFxCMUrFW2MQirBv19Mi8hhl8TP\n" +
                "fu/ZcdQbRKAmMFaghqAif41AvQ9AjWE0iEHFMPK16RQToUWQYoYJbu8XwszZJneLimpc7SDaAyYF\n" +
                "DIUzm/V7rWT4lGjr/lMp6Z/uoVMEjK0lSpkdplzRWl9kMPHyTmQeVZlRMmIzaW2C6oZubkV3UJ31\n" +
                "0nsZdoQbORbm+AJbTz3wDTPKHX6RPpJtY9E8429TlYayk5YJJfFhvIN2JZsOW1f+ah880A/b84H5\n" +
                "jHPOm9rP9MhcT0SuXcZa55YD/HnyZ4Dnpa64CBJZVR4p4LZ9PK2wusZlWTSWurdumvq6k4+NX9t9\n" +
                "7brf+wOJ5ZvpDQplbmRzdHJlYW0NCmVuZG9iag0KMjAgMCBvYmoNCjw8L0ZpbHRlci9GbGF0ZURl\n" +
                "Y29kZS9MZW5ndGggMjQ1Pj4NCnN0cmVhbQ0KeJxdkE1qwzAQhfc6xSyTRZBtcErBGIpDwYu0pW4P\n" +
                "IEtjV1BLQpYXvn2lUUigAxJ8vHnzx7v+0hsdgH94KwcMMGmjPK528xJhxFkbVhagtAw3ol8uwjEe\n" +
                "zcO+Blx6M1nWNMA/o7gGv8PhRdkRj4y/e4VemxkO390Qedic+8UFTYCCtS0onGKhq3BvYkHgZDv1\n" +
                "Kuo67KfoeWR87Q6hIi7zMNIqXJ2Q6IWZkTVFjBaa1xgtQ6P+6XV2jZP8Ef6RXdRFS1QRVZnKC9G5\n" +
                "JKqydu4yPRM91dTlVi/1S2e5LyM37+MedDtaII2uDd7P66xLrvT+ABHdelUNCmVuZHN0cmVhbQ0K\n" +
                "ZW5kb2JqDQoyMSAwIG9iag0KPDwvRmlsdGVyL0ZsYXRlRGVjb2RlL0xlbmd0aCAxNjk2L0xlbmd0\n" +
                "aDEgMzA2OD4+DQpzdHJlYW0NCnictVZ7aFtVGP/OObm5SZMmfeTRtWt20tt3Hk2TNGk7aerWFcus\n" +
                "rxZxKtUsuW2ytU2WprX1hfhAGCrzHycbiOCYQ/wjbsM3qKCCoiIo/uPQ+fhDwWl9MSdr43dvb2u3\n" +
                "dEMRzyHn/r739517zncDBADK4T5gcN21Ix3BeO3eLwHITci9JjEVz55d+HMXAL0VebsTc3luP2PL\n" +
                "AwjzKN8znp2YGiAH9wOwN1E+NzG5MF774BmUCd+izXhKjiePP7f0JOqewl8khQxxM21GXRvSjamp\n" +
                "/Hx5NzyN9DGkj09mEvGyMePHAIarkR6bis9nWR85gfJupPl0fEp+77itDeXPY0xfNjOTL94L6Muk\n" +
                "+OfZnJx1f/BSLcZX9GOg1MYArj+/cOdt1it+B8K+O1oIPw7aOPbdQ6eKxWI7O89cSOqBagK0Y65l\n" +
                "M67nUf4LO696Wj88KscPXYp/HBTMaI+Z0B/I3IoKnSMHQIDSgZZmqyAQSqhAKb1YnBxJpKF/kS/O\n" +
                "Ml5sVzKBz9DoN804soHH/zjIMGQ34rPPYVOJ7nFwXkAf2hhrg2o7Z1vZJ4LvhuhhbTsJVXUuClHK\n" +
                "uuxwbsCr/xs2qCseurZ/4/R/Hjq4ClcLGLBUPb7r4cX44uRibnG2WAS4kLZ+bX3f+rr1RetJ68mS\n" +
                "cwj9FVZLudlUZjSIekHHKAHvC4Q1beODPBVPFliT+hy5SeqQluo6pDO7nD3OKwqsvUC2V6jA562s\n" +
                "cvb4vEj5vG/5vIUdcwUi9RWgokA+LYC0DalthaG5AuW8ANv7CqTi7UEE0gC6wGeB3M4VtZFCkyzN\n" +
                "r9ivWq2awOVN+I7UgM/r8WHmQtO22JnSFNWSYkulgpIyd2GNPm9M+S1dQqxUe+ntuYRflKF8SZPj\n" +
                "vjf31zkEu9mmM5kFsIUbBWmzpKvbLIAEK29Drx+CADz6Mis+VO+AufbampjH41GX2hql2+ixZ1TA\n" +
                "ZthU/BFf5CDcTL+h5+BZuB9vYxJ2wlbohihEIIwntxUa8ZzboBp71Vn2PfuWmslL5DA5RPaQndgd\n" +
                "v4F30G6farkVrcIQhE7woZ1ygyk+gXxAD+MtFAGq3ZXu6kp3ZSs5snyrjdxNDy/J9OGlR+kMyrPF\n" +
                "n+A05mHCeBI09bv0FigzW8xVZTUWl2hiItRZKbjtFDzMCKKnE9oD1UGH3aaXGpq7wpFL4WM1nNc4\n" +
                "GhocFz3puVVy+UQpUrrBpuLP7A1ShErg/XYzqdJDuWgRy3RUBB0JALMqScQ6OmKeQNQuVYbsbj9p\n" +
                "iTqcerElEt0ZDDJSn26SkgcPJqWF4KnT5I/T9ff4pTsOHLhDOtiJe+Ms/koO0CO4y2HY2h8Itbe4\n" +
                "Kk0g+iipbfSV2Q1qM6J1FtFAxTpDjcFmCLXbKk0GEBtb6srV4LFYOwbXh4KRaMThdBHRQrDqFgsR\n" +
                "7bbmFj+J9pFoJNpHQ0EHHozqSLPUgDK9GDlae63b08TKKGXMHfF0+kW3sVVkhFDLTkqqzEO7XF2G\n" +
                "8gZ5n1nYYu/tsZQbH3jrtS1NioKoa2vI+V3GRsIYYUZqHiCD8pVP70u1pZ4gZMst08/0hrseUTpG\n" +
                "8VXszIN43ur7HbDydbMLBqMo6AjoJD1Ta/AEhKi7y93iJBbb8hekwfbIYLgxPKja/4j2VXg2qvpN\n" +
                "pIaJJlGiULZi1OW2u8mh5fGqRrUd2f/VHFs3H1ubyyuTuNQZWp2q/wp4d60HTsDqh4RgH53TMMUT\n" +
                "/oCGGd6uCQ3r1ukIePP2a1iP/Cc0LEIHPKV8qXRGpD6CDzVMoIK8omEKFvKJhhl0k8Ma1q3TEaCG\n" +
                "fKVhPfLPaViEMWrcnsku5NITqTzv7OkJ+HqCfPcCH5Bn0hPTHD/98nRC9vKh6YSft4Y6A208HAr5\n" +
                "ApHurq08G8/leWacD8fzqdGFrMynEMi4pBPxSS7vm0WUmeZyMp3P5C702Ds8ynfM53PxXgwavEGe\n" +
                "mJ2M526UczOKQcgf6Bwe9anyNd/pGR7nyEhigNxeJexGOcJ2yGDTWYAcpHGrU5AHjo2nB2cA208P\n" +
                "tiEOu1HOYQBkmFG1ppEagQRiGXECVy9yhlTsR9QKIfQRwAvJ8UqGcPqQimA77MLmxjFeHOMpkTIw\n" +
                "jusw0nmMPYpxsuiNw5TGkTWURs9xmESJjE1yVuNl1ExkbJlppDPo83I59mKcUeTugHnUzqGPXq3S\n" +
                "INyA8gn0O6lmdiNSOdXPSoQQVhVAXcXet86+NO80WnHkck0jqVWQg71r1f7TfVTPvzqKdyn/TEvH\n" +
                "X8t5/1sNCmVuZHN0cmVhbQ0KZW5kb2JqDQoyMiAwIG9iag0KWyAwWyA1MDBdICAyWyAzMTBdICAy\n" +
                "OVsgOTg3XSAgMzRbIDQzNF0gIDQxWyA0MTBdIF0gDQplbmRvYmoNCjIzIDAgb2JqDQo8PC9UeXBl\n" +
                "L1hSZWYvU2l6ZSAyMy9XWyAxIDQgMl0gL1Jvb3QgMSAwIFIvSW5mbyAxMiAwIFIvSURbPDhEMzcz\n" +
                "QTU3RTY3MzZDNDU5NUZCMDVGMzNBQzY5NUJCPjw4RDM3M0E1N0U2NzM2QzQ1OTVGQjA1RjMzQUM2\n" +
                "OTVCQj5dIC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDkwPj4NCnN0cmVhbQ0KeJxjYACC//8Z\n" +
                "gaQgAwOIqoVQW8EU40EwxXQUTDF7QKgUMMXCBKG8wRSrK4SqhlAbgZqApggzMEEoZgjFAqEYIRRU\n" +
                "CStQA9sCsD4OJTDFmQSmBKIZGADBLwmVDQplbmRzdHJlYW0NCmVuZG9iag0KeHJlZg0KMCAyNA0K\n" +
                "MDAwMDAwMDAxMyA2NTUzNSBmDQowMDAwMDAwMDE3IDAwMDAwIG4NCjAwMDAwMDAxMjUgMDAwMDAg\n" +
                "bg0KMDAwMDAwMDE4MSAwMDAwMCBuDQowMDAwMDAwNDQ5IDAwMDAwIG4NCjAwMDAwMDA3MDkgMDAw\n" +
                "MDAgbg0KMDAwMDAwMDg0MCAwMDAwMCBuDQowMDAwMDAwODY4IDAwMDAwIG4NCjAwMDAwMDEwMjYg\n" +
                "MDAwMDAgbg0KMDAwMDAwMTA5OSAwMDAwMCBuDQowMDAwMDAxMzQ5IDAwMDAwIG4NCjAwMDAwMDE0\n" +
                "MDMgMDAwMDAgbg0KMDAwMDAwMTQ1NyAwMDAwMCBuDQowMDAwMDAwMDE0IDY1NTM1IGYNCjAwMDAw\n" +
                "MDAwMTUgNjU1MzUgZg0KMDAwMDAwMDAxNiA2NTUzNSBmDQowMDAwMDAwMDE3IDY1NTM1IGYNCjAw\n" +
                "MDAwMDAwMTggNjU1MzUgZg0KMDAwMDAwMDAxOSA2NTUzNSBmDQowMDAwMDAwMDAwIDY1NTM1IGYN\n" +
                "CjAwMDAwMDIwODIgMDAwMDAgbg0KMDAwMDAwMjQwMiAwMDAwMCBuDQowMDAwMDA0MTg3IDAwMDAw\n" +
                "IG4NCjAwMDAwMDQyNTggMDAwMDAgbg0KdHJhaWxlcg0KPDwvU2l6ZSAyNC9Sb290IDEgMCBSL0lu\n" +
                "Zm8gMTIgMCBSL0lEWzw4RDM3M0E1N0U2NzM2QzQ1OTVGQjA1RjMzQUM2OTVCQj48OEQzNzNBNTdF\n" +
                "NjczNkM0NTk1RkIwNUYzM0FDNjk1QkI+XSA+Pg0Kc3RhcnR4cmVmDQo0NTQ4DQolJUVPRg0KeHJl\n" +
                "Zg0KMCAwDQp0cmFpbGVyDQo8PC9TaXplIDI0L1Jvb3QgMSAwIFIvSW5mbyAxMiAwIFIvSURbPDhE\n" +
                "MzczQTU3RTY3MzZDNDU5NUZCMDVGMzNBQzY5NUJCPjw4RDM3M0E1N0U2NzM2QzQ1OTVGQjA1RjMz\n" +
                "QUM2OTVCQj5dIC9QcmV2IDQ1NDgvWFJlZlN0bSA0MjU4Pj4NCnN0YXJ0eHJlZg0KNTE4NA0KJSVF\n" +
                "T0Y=";
    }

}
