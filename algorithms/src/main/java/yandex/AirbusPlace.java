package yandex;

import java.util.*;
import java.util.function.Predicate;

public class AirbusPlace {

    private static final char FREE_PLACE = '.';
    private static final char BUSY_PLACE = '#';
    private static final char CHOSEN_PLACE = 'X';
    private static final String LEFT_SIDE = "left";
    private static final String RIGHT_SIDE = "right";
    private static final String WINDOWS = "window";
    private static final String AISLE = "aisle";
    private static final String DELIMITER = "_";
    private static final String FAIL_RESULT = "Cannot fulfill passengers requirements";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());
        Map<String, List<RowDto>> rowMap = new HashMap<>(3, 1);
        List<RowDto> left = new ArrayList<>();
        List<RowDto> right = new ArrayList<>();
        rowMap.put(LEFT_SIDE, left);
        rowMap.put(RIGHT_SIDE, right);
        for (int i = 0; i < rows; i++) {
            String row = sc.nextLine();
            String[] rowSides = row.split(DELIMITER);

            RowDto leftSide = calculateRowInfo(rowSides[0], i, LEFT_SIDE);
            left.add(leftSide);

            RowDto rightSide = calculateRowInfo(rowSides[1], i, RIGHT_SIDE);
            right.add(rightSide);
        }

        int passengerGroupNums = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < passengerGroupNums; i++) {
            String prerequisites = sc.nextLine();
            doSomething(prerequisites, rowMap);
        }

    }

    private static void doSomething(String requirements, Map<String, List<RowDto>> rowMap) {
        String[] prerequisites = requirements.split(" ");
        if (prerequisites.length != 3) {
            throw new RuntimeException("Unexpected requirements length. Requirements: " + requirements);
        }
        int passengerLength = Integer.parseInt(prerequisites[0]);
        String side = prerequisites[1];
        String requirement = prerequisites[2];

        Optional<RowDto> optionalRowDto = rowMap.get(side).stream()
                .filter(RowDto.requirementFilter(requirement, passengerLength))
                .min(RowDto.rowNumberComparator());
        if (optionalRowDto.isPresent()) {
            RowDto result = optionalRowDto.get();
            List<String> seats = modifyRow(result, passengerLength, side, requirement);
            printResult(rowMap, seats);
            result.rowFilling = result.rowFilling.replace(CHOSEN_PLACE, BUSY_PLACE);
        } else {
            System.out.println(FAIL_RESULT);
        }
    }

    /**
     * Output result.
     * <p>
     * Also, should update {@link RowDto} info
     * @param rowDto chosen row
     */
    private static List<String> modifyRow(RowDto rowDto, int passengerLength, String side, String requirement) {
        String row = rowDto.rowFilling;
        char[] chars = row.toCharArray();
        List<String> seats = new ArrayList<>(passengerLength + 1);
        if (AISLE.equals(requirement)) {
            if (LEFT_SIDE.equals(side)) {
                for (int i = 2; passengerLength > 0; i--, passengerLength--) {
                    chars[i] = CHOSEN_PLACE;
                    seats.add(String.format("%s%s", rowDto.rowNumber + 1, letterBySideAndSeatNumber(side, i)));
                }
            } else {
                for (int i = 0; i < passengerLength; i++) {
                    chars[i] = CHOSEN_PLACE;
                    seats.add(String.format("%s%s", rowDto.rowNumber + 1, letterBySideAndSeatNumber(side, i)));
                }
            }
        } else {
            if (RIGHT_SIDE.equals(side)) {
                for (int i = 2; passengerLength > 0; i--, passengerLength--) {
                    chars[i] = CHOSEN_PLACE;
                    seats.add(String.format("%s%s", rowDto.rowNumber + 1, letterBySideAndSeatNumber(side, i)));
                }
            } else {
                for (int i = 0; i < passengerLength; i++) {
                    chars[i] = CHOSEN_PLACE;
                    seats.add(String.format("%s%s", rowDto.rowNumber + 1, letterBySideAndSeatNumber(side, i)));
                }
            }
        }

        RowDto modifiedDto = calculateRowInfo(String.valueOf(chars), rowDto.rowNumber, side);
        rowDto.rowFilling = modifiedDto.rowFilling;
        rowDto.isWindowFree = modifiedDto.isWindowFree;
        rowDto.isAisleFree = modifiedDto.isAisleFree;
        rowDto.freePlacesInARowAisle = modifiedDto.freePlacesInARowAisle;
        rowDto.freePlacesInARowWindow = modifiedDto.freePlacesInARowWindow;
        return seats;
    }

    private static char letterBySideAndSeatNumber(String side, int seatNum) {
        if (LEFT_SIDE.equals(side)) {
            return (char) (65 + seatNum);
        } else {
            return (char) (68 + seatNum);
        }
    }

    private static void printResult(Map<String, List<RowDto>> rowMap, List<String> seats) {
        List<RowDto> left = rowMap.get(LEFT_SIDE);
        List<RowDto> right = rowMap.get(RIGHT_SIDE);

        seats.sort(Comparator.comparing(str -> str.charAt(1)));

        String msg = String.format("Passengers can take seats: " + String.join(" ", seats));
        System.out.println(msg);
        for (int i = 0; i < left.size(); i++) {
            String row = String.join(DELIMITER, left.get(i).rowFilling, right.get(i).rowFilling);
            System.out.println(row);
        }
    }

    /**
     * Creates instance of {@link RowDto}.
     *
     * @param row current triple seats filling
     * @param rowNumber number of row
     * @param side left or right depending on {@link AirbusPlace#DELIMITER}
     * @return {@link RowDto}
     */
    private static RowDto calculateRowInfo(String row, int rowNumber, String side) {
        RowDto rowDto = new RowDto();
        rowDto.rowNumber = rowNumber;
        rowDto.rowFilling = row;

        if (LEFT_SIDE.equals(side)) {
            //Calculate info about window
            rowDto.isWindowFree = FREE_PLACE == row.charAt(0);
            if (rowDto.isWindowFree) {
                int length = 1;
                for (int i = 1; i < row.length(); i++) {
                    if (row.charAt(i) == FREE_PLACE) {
                        length += 1;
                    } else {
                        break;
                    }
                }
                rowDto.freePlacesInARowWindow = length;
            }

            //Calculate info about aisle
            rowDto.isAisleFree = FREE_PLACE == row.charAt(2);
            if (rowDto.isAisleFree) {
                int length = 1;
                for (int i = 1; i >= 0; i--) {
                    if (row.charAt(i) == FREE_PLACE) {
                        length += 1;
                    } else {
                        break;
                    }
                }
                rowDto.freePlacesInARowAisle = length;
            }

        } else {
            //Calculate info about window
            rowDto.isWindowFree = FREE_PLACE == row.charAt(2);
            if (rowDto.isWindowFree) {
                int length = 1;
                for (int i = 1; i >= 0; i--) {
                    if (row.charAt(i) == FREE_PLACE) {
                        length += 1;
                    } else {
                        break;
                    }
                }
                rowDto.freePlacesInARowWindow = length;
            }

            //Calculate info about aisle
            rowDto.isAisleFree = FREE_PLACE == row.charAt(0);
            if (rowDto.isAisleFree) {
                int length = 1;
                for (int i = 1; i < row.length(); i++) {
                    if (row.charAt(i) == FREE_PLACE) {
                        length += 1;
                    } else {
                        break;
                    }
                }
                rowDto.freePlacesInARowAisle = length;
            }
        }

        return rowDto;
    }

    /**
     * Dto that contains info about current row.
     */
    private static final class RowDto {

        public int rowNumber;

        public String rowFilling;

        public boolean isWindowFree;

        public boolean isAisleFree;

        public int freePlacesInARowWindow = 0;

        public int freePlacesInARowAisle = 0;

        public static Predicate<RowDto> requirementFilter(String requirement, int passengerQuantity) {
            if (AISLE.equals(requirement)) {
                return rowDto -> rowDto.isAisleFree && passengerQuantity <= rowDto.freePlacesInARowAisle;
            } else {
                return rowDto -> rowDto.isWindowFree && passengerQuantity <= rowDto.freePlacesInARowWindow;
            }
        }

        public static Comparator<RowDto> rowNumberComparator() {
            return Comparator.comparing(rowDto -> rowDto.rowNumber);
        }
    }
}
