package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Calendar Matching
 *
 * Imagine that you want to schedule a meeting of a certain duration with a co-worker.
 * You have access to your calendar and your co-worker's calendar (both of which contain
 * your respective meetings for the day, in the form of [startTime, endTime], as well as
 * both of your daily bounds (i.e., the earliest and latest times at which you're
 * available for meetings every day, in the form of [earliestTime, latestTime]).
 *
 * Write a function that takes in your calendar, your daily bounds, your co-worker's
 * calendar, your co-worker's daily bounds, and the duration of meeting that you want
 * to schedule, and that returns a list of all the time blocks (in the form of [startTime,
 * endTime]) during which you could schedule the meeting, ordered from earliest time
 * block to latest.
 *
 * Note that the given calendar times will be sorted by start time in ascending order,
 * as you would expect them to appear in a calendar application like google calendar.
 *
 * Sample Input:
 * {
 *   "calendar1": [
 *     ["9:00", "10:30"],
 *     ["12:00", "13:00"],
 *     ["16:00", "18:00"]
 *   ],
 *   "dailyBounds1": ["9:00", "20:00"],
 *   "calendar2": [
 *     ["10:00", "11:30"],
 *     ["12:30", "14:30"],
 *     ["14:30", "15:00"],
 *     ["16:00", "17:00"]
 *   ],
 *   "dailyBounds2": ["10:00", "18:30"],
 *   "meetingDuration": 30
 * }
 */
public class CalendarMatching {
    // Time: O(C1 + C2), Space : O(C1 + C2)
    // C1 and C2 are the respective number of meetings in calendar1 and calendar2.
    public static List<StringMeeting> calendarMatching(
            List<StringMeeting> calendar1,
            StringMeeting dailyBounds1,
            List<StringMeeting> calendar2,
            StringMeeting dailyBounds2,
            int meetingDuration) {
        // Write your code here.
        List<Meeting>updatedCalendar1 = updateCalendar(calendar1, dailyBounds1);
        List<Meeting>updatedCalendar2 = updateCalendar(calendar2, dailyBounds2);
        List<Meeting> mergedCalendar = mergeCalendars(updatedCalendar1, updatedCalendar2);
        List<Meeting> flattenedCalendar = flattenCalendar(mergedCalendar);
        return getMatchingAvailabilities(flattenedCalendar, meetingDuration);
    }

    private static List<Meeting> updateCalendar(List<StringMeeting> calendar,
                                                StringMeeting dailyBound) {
        List<StringMeeting> updatedCalendar = new ArrayList<StringMeeting>();
        updatedCalendar.add(new StringMeeting("0:00", dailyBound.start));
        updatedCalendar.addAll(calendar);
        updatedCalendar.add(new StringMeeting(dailyBound.end, "23:59"));

        List<Meeting> calInMinutes = new ArrayList<Meeting>();
        for (int i = 0; i < updatedCalendar.size(); ++i) {
            calInMinutes.add(new Meeting(
                    timeToMinutes(updatedCalendar.get(i).start),
                    timeToMinutes(updatedCalendar.get(i).end)));
        }
        return calInMinutes;
    }

    private static List<Meeting> mergeCalendars(List<Meeting> calendar1, List<Meeting> calendar2) {
        List<Meeting> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < calendar1.size() && j < calendar2.size()) {
            Meeting meeting1 = calendar1.get(i);
            Meeting meeting2 = calendar2.get(j);

            if (meeting1.start < meeting2.start) {
                merged.add(meeting1);
                ++i;
            } else {
                merged.add(meeting2);
                ++j;
            }
        }

        while (i < calendar1.size()) {
            merged.add(calendar1.get(i));
            ++i;
        }

        while (j < calendar2.size()) {
            merged.add(calendar2.get(j));
            ++j;
        }

        return merged;
    }

    private static List<Meeting>
    flattenCalendar(List<Meeting> calendar) {
        List<Meeting> flattened = new ArrayList<Meeting>();
        flattened.add(calendar.get(0));

        for (int i = 1; i < calendar.size(); ++i) {
            Meeting currMeeting = calendar.get(i);
            Meeting prevMeeting = flattened.get(flattened.size() - 1);

            if (prevMeeting.end >= currMeeting.start) {
                Meeting newPrevMeeting =
                        new Meeting(prevMeeting.start, Math.max(prevMeeting.end, currMeeting.end));
                flattened.set(flattened.size() - 1, newPrevMeeting);
            } else {
                flattened.add(currMeeting);
            }
        }
        return flattened;
    }

    private static List<StringMeeting>
    getMatchingAvailabilities(List<Meeting> calendar, int meetingDuration) {

        List<Meeting> matchingAvailabilities = new ArrayList<Meeting>();
        for (int i = 1; i < calendar.size(); ++i) {
            int start = calendar.get(i - 1).end;
            int end = calendar.get(i).start;
            int availableDuration = end - start;
            if (availableDuration >= meetingDuration) {
                matchingAvailabilities.add(new Meeting(start, end));
            }
        }

        List<StringMeeting> matchingAvailabilitiesInHours = new ArrayList<>();
        for (int i = 0; i < matchingAvailabilities.size(); ++i) {
            matchingAvailabilitiesInHours.add(
                    new StringMeeting(
                            minutesToTime(matchingAvailabilities.get(i).start),
                            minutesToTime(matchingAvailabilities.get(i).end))
            );
        }
        return matchingAvailabilitiesInHours;
    }

    private static int timeToMinutes(String time) {
        String [] digits = time.split(":");
        int hour = Integer.parseInt(digits[0]);
        int min = hour * 60;
        int total = min + Integer.parseInt(digits[1]);
        return total;
    }

    private static String minutesToTime(int time) {
        int hour = time/60;
        int min = time%60;
        String m = min == 0 ? "00" : Integer.toString(min);
        return Integer.toString(hour) + ":" + m;
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
