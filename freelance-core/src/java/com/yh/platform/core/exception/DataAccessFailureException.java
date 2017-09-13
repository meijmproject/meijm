package com.yh.platform.core.exception;

import java.util.Arrays;
import java.util.List;


public class DataAccessFailureException extends ServiceException {
//definition the oracle error code which process enable
	public static final String[] ORA_CODE = new String[]{
		"ORA-00001",//unique constraint violated
		"ORA-00018",//maximum number of sessions exceeded
		"ORA-00022",//invalid session ID; access denied
		"ORA-01688",//unable to extend tablespace
		"ORA-02291",//parent key not found
		"ORA-02292",//child record found
		"ORA-12547", //TNS:lost contact
		"ORA-01401",//inserted value too large for column
		"ORA-01407",//can`t insert null to column
		"ORA-12899",// VALUE TOO LARGE FOR COLUMN
		"DB2-803",
		"DB2-204",
		"DB2-1581",
		"DB2-532",
		"DB2-727",
		"DB2-4470",
		"DB2-404",
		"DB2-1585"
		};
	public static List<String> oracleCode = Arrays.asList(ORA_CODE);

    /**
     * uid
     */
    private static final long serialVersionUID = -5103702986862077384L;

    /**
     * construct an exception with null as the detailed message
     */
    public DataAccessFailureException() {
        super();
    }

    public DataAccessFailureException(Throwable cause) {
        super(null, cause);
    }

    /**
     * construct an exception with the specified error message
     *
     * @param errorKey
     */
    public DataAccessFailureException(String errorKey) {
        super(errorKey);
    }

    /**
     * construct an exception with error message and root cause
     *
     * @param message
     * @param cause
     */
    public DataAccessFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Try to translate the ORACLE DB ERROR into the localized user message for the front-end user.
     */
    public String getErrorKey() {
        String errorKey = "error.dao.common";

        if (this.getCause() != null) {
            Throwable tr = getCause();
            String msg = this.getCause().getMessage();
            while (msg != null && tr.getCause() != null && (msg.indexOf("ORA-") < 0 && msg.indexOf("SQLCODE=") < 0)) {
                tr = tr.getCause();
                msg = tr.getMessage();
            }

            String oracode = "";
            if (msg != null) {
                if (msg.indexOf("ORA-") >= 0) {
                    int start = msg.indexOf("ORA");
                    int end = msg.indexOf(":", start);

                    oracode = msg.substring(start, end);
                } else if (msg.indexOf("SQLCODE=") >= 0) {
                    int start = msg.indexOf("SQLCODE=");
                    int end = msg.indexOf(",", start);

                    oracode = "DB2" + msg.substring(start, end).substring(8);
                }


                if (oracleCode.contains(oracode))
                    errorKey = oracode;
            }
            return errorKey;
        } else
            return super.getErrorKey();
    }
}
