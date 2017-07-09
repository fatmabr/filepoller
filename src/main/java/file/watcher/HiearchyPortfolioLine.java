package file.watcher;

import file.watcher.line.Line;

/**
 * Created by bradai on 28/06/2017.
 */
public class HiearchyPortfolioLine extends Line {

    private String compID;
    private  String compoCode;
    private boolean compoActif;
    private boolean compIsGroup;
    private Integer compTyp;
    private Integer compPosSetailRepo;
    private String compVarReport;
    private String compStressReport	;
    private String compPNLReport;

    public HiearchyPortfolioLine(String[] line) {
        compID = line[0];
        compoCode = line[1];
        //...
    }

    public String getCompID() {
        return compID;
    }

    public String getCompoCode() {
        return compoCode;
    }

    public boolean isCompoActif() {
        return compoActif;
    }

    public boolean isCompIsGroup() {
        return compIsGroup;
    }

    public Integer getCompTyp() {
        return compTyp;
    }

    public Integer getCompPosSetailRepo() {
        return compPosSetailRepo;
    }

    public String getCompVarReport() {
        return compVarReport;
    }

    public String getCompStressReport() {
        return compStressReport;
    }

    public String getCompPNLReport() {
        return compPNLReport;
    }
}
