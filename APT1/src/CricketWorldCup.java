public class CricketWorldCup {
        int low;
        int high;
        String[] n;
        String[] lost;

        public String[] standings(String[] _n, String[] _lost) {
                lost = _lost;
                n = _n;
                int[] wins = new int[n.length];
                for (int a = 0; a < n.length; a++) {
                        wins[a] = 0;
                }
                for (int a = 0; a < n.length; a++) {
                        for (int b = 0; b < lost.length; b++) {
                                if (n[a].equals(lost[b])) {
                                        wins[a]++;
                                }
                        }
                }
                String sTemp;
                int iTemp;
                for (int a = 0; a < wins.length - 1; a++) {
                        for (int b = a + 1; b < wins.length; b++) {
                                if (wins[a] < wins[b]) {
                                        iTemp = wins[a];
                                        wins[a] = wins[b];
                                        wins[b] = iTemp;
                                        sTemp = n[a];
                                        n[a] = n[b];
                                        n[b] = sTemp;
                                        sTemp = lost[a];
                                        lost[a] = lost[b];
                                        lost[b] = sTemp;
                                }
                        }
                }
                int cur = wins[0];
                low = 0;
                high = 0;
                for (int a = 1; a < n.length; a++) {
                        if (wins[a] == cur) {
                                high = a;
                        } else {
                                fix();
                                cur = wins[a];
                                low = a;
                                high = a;
                        }
                }
                high = n.length - 1;
                fix();
                return n;
        }

        public void fix() {
                String sTemp;
                for (int a = low; a <= high - 1; a++) {
                        for (int b = a + 1; b <= high; b++) {
                                if (getRank(lost[a]) > getRank(lost[b])) {
                                        sTemp = n[a];
                                        n[a] = n[b];
                                        n[b] = sTemp;
                                        sTemp = lost[a];
                                        lost[a] = lost[b];
                                        lost[b] = sTemp;
                                }
                        }
                }
        }

        public int getRank(String team) {
                for (int a = 0; a < n.length; a++) {
                        if (n[a].equals(team)) {
                                return a;
                        }
                }
                return -1;
        }
}