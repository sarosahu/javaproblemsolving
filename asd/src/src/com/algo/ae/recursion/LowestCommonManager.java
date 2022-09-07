package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonManager {
    public static OrgChart getLowestCommonManager(
            OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
        // Write your code here.
        return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
    }

    public static OrgInfo getOrgInfo(OrgChart manager,
                                     OrgChart reportOne,
                                     OrgChart reportTwo) {
        int numImportantReports = 0;
        for (OrgChart directReport : manager.directReports) {
            OrgInfo orgInfo = getOrgInfo(directReport, reportOne, reportTwo);
            if (orgInfo.lowestCommonManager != null) {
                return orgInfo;
            }
            numImportantReports += orgInfo.numImportantReports;
        }
        if (manager == reportOne || manager == reportTwo) {
            numImportantReports++;
        }
        OrgChart lowestCommonManager = numImportantReports == 2 ? manager : null;
        OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager, numImportantReports);
        return newOrgInfo;
    }

    static class OrgChart {
        public char name;
        public List<OrgChart> directReports;

        OrgChart(char name) {
            this.name = name;
            this.directReports = new ArrayList<OrgChart>();
        }

        public void addDirectReports(OrgChart[] directReports) {
            for (OrgChart directReport : directReports) {
                this.directReports.add(directReport);
            }
        }
    }

    static class OrgInfo {
        public OrgChart lowestCommonManager;
        int numImportantReports;

        OrgInfo(OrgChart lowestCommonManager, int numImportantReports) {
            this.lowestCommonManager = lowestCommonManager;
            this.numImportantReports = numImportantReports;
        }
    }

    public static void main(String[] args) {
        OrgChart top = new OrgChart('A');
        OrgChart[] directs = new OrgChart[] {
                new OrgChart('B'),
                new OrgChart('C'),
                new OrgChart('D'),
                new OrgChart('E'),
        };
        top.addDirectReports(directs);

        top.directReports.get(0).addDirectReports(
                new OrgChart[] {
                        new OrgChart('F'),
                        new OrgChart('G'),
                }
        );
        OrgChart m1 = top.directReports.get(0).directReports.get(0);
        top.directReports.get(0).directReports.get(1).addDirectReports(
                new OrgChart[] {
                        new OrgChart('I'),
                        new OrgChart('J'),
                }
        );
        OrgChart m2 = top.directReports.get(0).directReports.get(1).directReports.get(1);
        OrgChart lcm = getLowestCommonManager(top, m1, m2);
    }
}
