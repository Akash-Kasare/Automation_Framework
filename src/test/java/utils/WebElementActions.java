package utils;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import java.time.Duration;
import java.util.List;

/**
 * WebElementActions - Reusable Web Element Interaction Methods
 *
 * Provides common UI interaction methods with:
 * - Locator as parameter (no hardcoding)
 * - Explicit waits (no implicit waits or hardcoded sleeps)
 * - Comprehensive logging
 * - Error handling
 *
 * Usage: WebElementActions.clickElement(By.id("button"), 10);
 */
public class WebElementActions extends BaseClass {

    /**
     * Reusable explicit wait with 30 seconds timeout
     */
    private static WebDriverWait getExplicitWait() {
        return new WebDriverWait(BaseClass.driver, Duration.ofSeconds(30));
    }

    // ==================== CLICK OPERATIONS ====================

    /**
     * Click an element with explicit wait
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait for element to be clickable
     */
    public static void clickElement(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Click Element: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            TestLogger.action("Element", "Click", locator.toString());
            element.click();
            TestLogger.stepPassed("Element clicked successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click element", e);
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
    }

    /**
     * Click element with default wait time (10 seconds)
     * @param locator - Element locator
     */
    public static void clickElement(By locator) {
        clickElement(locator, 10);
    }

    /**
     * Double click an element
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void doubleClickElement(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Double Click Element: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Actions actions = new Actions(BaseClass.driver);
            TestLogger.action("Element", "Double Click", locator.toString());
            actions.doubleClick(element).perform();
            TestLogger.stepPassed("Element double clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to double click element", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Right click (context click) an element
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void rightClickElement(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Right Click Element: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Actions actions = new Actions(BaseClass.driver);
            TestLogger.action("Element", "Right Click", locator.toString());
            actions.contextClick(element).perform();
            TestLogger.stepPassed("Element right clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to right click element", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Click element using JavaScript (useful for hidden elements)
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void clickElementByJS(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Click Element by JavaScript: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            TestLogger.action("Element", "Click by JS", locator.toString());
            js.executeScript("arguments[0].click();", element);
            TestLogger.stepPassed("Element clicked by JavaScript");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click element by JS", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== TEXT INPUT OPERATIONS ====================

    /**
     * Enter text in an element (clear first, then send keys)
     * @param locator - Element locator
     * @param text - Text to enter
     * @param waitTimeInSeconds - Time to wait
     */
    public static void enterText(By locator, String text, int waitTimeInSeconds) {
        TestLogger.stepStart("Enter Text: " + text);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            TestLogger.action("Text Field", "Enter", text);
            element.sendKeys(text);
            TestLogger.stepPassed("Text entered: " + text);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to enter text", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Enter text with default wait time (10 seconds)
     * @param locator - Element locator
     * @param text - Text to enter
     */
    public static void enterText(By locator, String text) {
        enterText(locator, text, 10);
    }

    /**
     * Append text to existing text (without clearing)
     * @param locator - Element locator
     * @param text - Text to append
     * @param waitTimeInSeconds - Time to wait
     */
    public static void appendText(By locator, String text, int waitTimeInSeconds) {
        TestLogger.stepStart("Append Text: " + text);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            TestLogger.action("Text Field", "Append", text);
            element.sendKeys(text);
            TestLogger.stepPassed("Text appended: " + text);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to append text", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Clear text in an element
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void clearText(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Clear Text");
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            TestLogger.action("Text Field", "Clear", "");
            element.clear();
            TestLogger.stepPassed("Text cleared");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to clear text", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get text from an element
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return Text content
     */
    public static String getText(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Get Text: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            TestLogger.stepPassed("Text retrieved: " + text);
            return text;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to get text", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get text with default wait time
     * @param locator - Element locator
     * @return Text content
     */
    public static String getText(By locator) {
        return getText(locator, 10);
    }

    // ==================== DROPDOWN/SELECT OPERATIONS ====================

    /**
     * Select option by visible text in a dropdown
     * @param locator - Dropdown locator
     * @param visibleText - Visible text to select
     * @param waitTimeInSeconds - Time to wait
     */
    public static void selectByVisibleText(By locator, String visibleText, int waitTimeInSeconds) {
        TestLogger.stepStart("Select Dropdown Option: " + visibleText);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(element);
            TestLogger.action("Dropdown", "Select by Text", visibleText);
            select.selectByVisibleText(visibleText);
            TestLogger.stepPassed("Option selected: " + visibleText);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to select option", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Select option by value in a dropdown
     * @param locator - Dropdown locator
     * @param value - Value to select
     * @param waitTimeInSeconds - Time to wait
     */
    public static void selectByValue(By locator, String value, int waitTimeInSeconds) {
        TestLogger.stepStart("Select Dropdown by Value: " + value);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(element);
            TestLogger.action("Dropdown", "Select by Value", value);
            select.selectByValue(value);
            TestLogger.stepPassed("Option selected: " + value);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to select option by value", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Select option by index in a dropdown
     * @param locator - Dropdown locator
     * @param index - Index to select
     * @param waitTimeInSeconds - Time to wait
     */
    public static void selectByIndex(By locator, int index, int waitTimeInSeconds) {
        TestLogger.stepStart("Select Dropdown by Index: " + index);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(element);
            TestLogger.action("Dropdown", "Select by Index", String.valueOf(index));
            select.selectByIndex(index);
            TestLogger.stepPassed("Option selected by index: " + index);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to select option by index", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all options from a dropdown
     * @param locator - Dropdown locator
     * @param waitTimeInSeconds - Time to wait
     * @return List of option texts
     */
    public static List<WebElement> getDropdownOptions(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Get Dropdown Options: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            TestLogger.stepPassed("Retrieved " + options.size() + " dropdown options");
            return options;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to get dropdown options", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== CHECKBOX/RADIO OPERATIONS ====================

    /**
     * Check a checkbox if not already checked
     * @param locator - Checkbox locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void checkCheckbox(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Checkbox: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (!element.isSelected()) {
                TestLogger.action("Checkbox", "Check", "");
                element.click();
                TestLogger.stepPassed("Checkbox checked");
            } else {
                TestLogger.info("Checkbox already checked");
            }
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to check checkbox", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Uncheck a checkbox if already checked
     * @param locator - Checkbox locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void uncheckCheckbox(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Uncheck Checkbox: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element.isSelected()) {
                TestLogger.action("Checkbox", "Uncheck", "");
                element.click();
                TestLogger.stepPassed("Checkbox unchecked");
            } else {
                TestLogger.info("Checkbox already unchecked");
            }
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to uncheck checkbox", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if checkbox is selected
     * @param locator - Checkbox locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if checked
     */
    public static boolean isCheckboxChecked(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Verify Checkbox Checked: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isChecked = element.isSelected();
            TestLogger.stepPassed("Checkbox checked status: " + isChecked);
            return isChecked;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to verify checkbox", e);
            return false;
        }
    }

    // ==================== ELEMENT VISIBILITY/PRESENCE OPERATIONS ====================

    /**
     * Check if element is visible
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if visible
     */
    public static boolean isElementVisible(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Element Visible: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            TestLogger.stepPassed("Element is visible");
            return true;
        } catch (Exception e) {
            TestLogger.info("Element is not visible");
            return false;
        }
    }

    /**
     * Check if element is present in DOM
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if present
     */
    public static boolean isElementPresent(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Element Present: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            TestLogger.stepPassed("Element is present");
            return true;
        } catch (Exception e) {
            TestLogger.info("Element is not present");
            return false;
        }
    }

    /**
     * Check if element is clickable
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if clickable
     */
    public static boolean isElementClickable(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Element Clickable: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            TestLogger.stepPassed("Element is clickable");
            return true;
        } catch (Exception e) {
            TestLogger.info("Element is not clickable");
            return false;
        }
    }

    /**
     * Wait for element to be invisible/disappear
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if invisible
     */
    public static boolean waitForElementToDisappear(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Wait for Element to Disappear: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            TestLogger.stepPassed("Element disappeared");
            return true;
        } catch (Exception e) {
            TestLogger.stepFailed("Element did not disappear", e);
            return false;
        }
    }

    // ==================== ATTRIBUTE OPERATIONS ====================

    /**
     * Get attribute value
     * @param locator - Element locator
     * @param attributeName - Attribute name
     * @param waitTimeInSeconds - Time to wait
     * @return Attribute value
     */
    public static String getAttribute(By locator, String attributeName, int waitTimeInSeconds) {
        TestLogger.stepStart("Get Attribute: " + attributeName);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String attributeValue = element.getAttribute(attributeName);
            TestLogger.stepPassed("Attribute value retrieved: " + attributeValue);
            return attributeValue;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to get attribute", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get CSS property value
     * @param locator - Element locator
     * @param propertyName - CSS property name
     * @param waitTimeInSeconds - Time to wait
     * @return CSS property value
     */
    public static String getCSSProperty(By locator, String propertyName, int waitTimeInSeconds) {
        TestLogger.stepStart("Get CSS Property: " + propertyName);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String propertyValue = element.getCssValue(propertyName);
            TestLogger.stepPassed("CSS property value: " + propertyValue);
            return propertyValue;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to get CSS property", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== MOUSE OPERATIONS ====================

    /**
     * Hover over an element
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void hoverOverElement(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Hover Over Element: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Actions actions = new Actions(BaseClass.driver);
            TestLogger.action("Element", "Hover", "");
            actions.moveToElement(element).perform();
            TestLogger.stepPassed("Element hovered");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to hover element", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Drag and drop element
     * @param sourceLocator - Source element locator
     * @param targetLocator - Target element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void dragAndDrop(By sourceLocator, By targetLocator, int waitTimeInSeconds) {
        TestLogger.stepStart("Drag and Drop Element");
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement source = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
            WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
            Actions actions = new Actions(BaseClass.driver);
            TestLogger.action("Element", "Drag and Drop", "");
            actions.dragAndDrop(source, target).perform();
            TestLogger.stepPassed("Element dragged and dropped");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to drag and drop", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== KEYBOARD OPERATIONS ====================

    /**
     * Send keys (like Tab, Enter, etc.)
     * @param locator - Element locator
     * @param key - Keys enum (e.g., Keys.ENTER)
     * @param waitTimeInSeconds - Time to wait
     */
    public static void sendSpecialKey(By locator, Keys key, int waitTimeInSeconds) {
        TestLogger.stepStart("Send Special Key: " + key);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            TestLogger.action("Keyboard", "Send Key", key.toString());
            element.sendKeys(key);
            TestLogger.stepPassed("Special key sent: " + key);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to send special key", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Press Enter key
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void pressEnter(By locator, int waitTimeInSeconds) {
        sendSpecialKey(locator, Keys.ENTER, waitTimeInSeconds);
    }

    /**
     * Press Tab key
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void pressTab(By locator, int waitTimeInSeconds) {
        sendSpecialKey(locator, Keys.TAB, waitTimeInSeconds);
    }

    /**
     * Press Escape key
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void pressEscape(By locator, int waitTimeInSeconds) {
        sendSpecialKey(locator, Keys.ESCAPE, waitTimeInSeconds);
    }

    // ==================== LIST/MULTIPLE ELEMENTS OPERATIONS ====================

    /**
     * Get all elements matching a locator
     * @param locator - Elements locator
     * @param waitTimeInSeconds - Time to wait
     * @return List of WebElements
     */
    public static List<WebElement> getElements(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Get All Elements: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            TestLogger.stepPassed("Retrieved " + elements.size() + " elements");
            return elements;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to get elements", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get count of elements
     * @param locator - Elements locator
     * @param waitTimeInSeconds - Time to wait
     * @return Count of elements
     */
    public static int getElementCount(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Get Element Count: " + locator);
        try {
            List<WebElement> elements = getElements(locator, waitTimeInSeconds);
            int count = elements.size();
            TestLogger.stepPassed("Element count: " + count);
            return count;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to get element count", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Click element from a list by text
     * @param locator - Elements locator
     * @param text - Text to match
     * @param waitTimeInSeconds - Time to wait
     */
    public static void clickElementByText(By locator, String text, int waitTimeInSeconds) {
        TestLogger.stepStart("Click Element by Text: " + text);
        try {
            List<WebElement> elements = getElements(locator, waitTimeInSeconds);
            for (WebElement element : elements) {
                if (element.getText().contains(text)) {
                    TestLogger.action("Element", "Click by Text", text);
                    element.click();
                    TestLogger.stepPassed("Element clicked: " + text);
                    return;
                }
            }
            throw new Exception("Element with text '" + text + "' not found");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click element by text", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== SCROLL OPERATIONS ====================

    /**
     * Scroll element into view
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     */
    public static void scrollToElement(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Scroll to Element: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            TestLogger.action("Page", "Scroll to Element", "");
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            TestLogger.stepPassed("Scrolled to element");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to scroll to element", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Scroll page to top
     */
    public static void scrollToTop() {
        TestLogger.stepStart("Scroll to Top");
        try {
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            TestLogger.action("Page", "Scroll to Top", "");
            js.executeScript("window.scrollTo(0, 0);");
            TestLogger.stepPassed("Scrolled to top");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to scroll to top", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Scroll page to bottom
     */
    public static void scrollToBottom() {
        TestLogger.stepStart("Scroll to Bottom");
        try {
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            TestLogger.action("Page", "Scroll to Bottom", "");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            TestLogger.stepPassed("Scrolled to bottom");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to scroll to bottom", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== JAVASCRIPT OPERATIONS ====================

    /**
     * Execute JavaScript
     * @param script - JavaScript to execute
     * @return Script execution result
     */
    public static Object executeJavaScript(String script) {
        TestLogger.stepStart("Execute JavaScript");
        try {
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            TestLogger.action("JavaScript", "Execute", script);
            Object result = js.executeScript(script);
            TestLogger.stepPassed("JavaScript executed");
            return result;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to execute JavaScript", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Set element value using JavaScript
     * @param locator - Element locator
     * @param value - Value to set
     * @param waitTimeInSeconds - Time to wait
     */
    public static void setValueByJS(By locator, String value, int waitTimeInSeconds) {
        TestLogger.stepStart("Set Value by JavaScript");
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
            TestLogger.action("Element", "Set Value by JS", value);
            js.executeScript("arguments[0].value = '" + value + "';", element);
            TestLogger.stepPassed("Value set by JavaScript");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to set value by JavaScript", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== WAIT OPERATIONS ====================

    /**
     * Wait for element to have specific text
     * @param locator - Element locator
     * @param text - Expected text
     * @param waitTimeInSeconds - Time to wait
     * @return true if text found
     */
    public static boolean waitForElementText(By locator, String text, int waitTimeInSeconds) {
        TestLogger.stepStart("Wait for Element Text: " + text);
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            TestLogger.stepPassed("Element contains text: " + text);
            return true;
        } catch (Exception e) {
            TestLogger.info("Element does not contain text: " + text);
            return false;
        }
    }

    /**
     * Wait for element attribute to have specific value
     * @param locator - Element locator
     * @param attributeName - Attribute name
     * @param attributeValue - Expected attribute value
     * @param waitTimeInSeconds - Time to wait
     * @return true if attribute matches
     */
    public static boolean waitForElementAttribute(By locator, String attributeName,
                                                   String attributeValue, int waitTimeInSeconds) {
        TestLogger.stepStart("Wait for Attribute: " + attributeName + " = " + attributeValue);
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.attributeToBe(locator, attributeName, attributeValue));
            TestLogger.stepPassed("Attribute matched: " + attributeName);
            return true;
        } catch (Exception e) {
            TestLogger.info("Attribute does not match");
            return false;
        }
    }

    /**
     * Wait for specific duration (use sparingly!)
     * @param milliseconds - Time to wait in milliseconds
     */
    public static void waitFor(long milliseconds) {
        TestLogger.stepStart("Wait for " + milliseconds + "ms");
        try {
            TestLogger.action("System", "Wait", milliseconds + "ms");
            Thread.sleep(milliseconds);
            TestLogger.stepPassed("Wait completed");
        } catch (Exception e) {
            TestLogger.stepFailed("Wait interrupted", e);
        }
    }

    /**
     * Check if alert is present
     * @param waitTimeInSeconds - Time to wait
     * @return true if alert present
     */
    public static boolean isAlertPresent(int waitTimeInSeconds) {
        TestLogger.stepStart("Check if Alert is Present");
        try {
            WebDriverWait wait = getExplicitWait();
            wait.until(ExpectedConditions.alertIsPresent());
            TestLogger.stepPassed("Alert is present");
            return true;
        } catch (Exception e) {
            TestLogger.info("Alert is not present");
            return false;
        }
    }

    /**
     * Accept alert
     */
    public static void acceptAlert() {
        TestLogger.stepStart("Accept Alert");
        try {
            Alert alert = BaseClass.driver.switchTo().alert();
            TestLogger.action("Alert", "Accept", alert.getText());
            alert.accept();
            TestLogger.stepPassed("Alert accepted");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to accept alert", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Dismiss alert
     */
    public static void dismissAlert() {
        TestLogger.stepStart("Dismiss Alert");
        try {
            Alert alert = BaseClass.driver.switchTo().alert();
            TestLogger.action("Alert", "Dismiss", alert.getText());
            alert.dismiss();
            TestLogger.stepPassed("Alert dismissed");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to dismiss alert", e);
            throw new RuntimeException(e);
        }
    }

    // ==================== ELEMENT STATE OPERATIONS ====================

    /**
     * Check if element is enabled
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if enabled
     */
    public static boolean isElementEnabled(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Element Enabled: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isEnabled = element.isEnabled();
            TestLogger.stepPassed("Element enabled status: " + isEnabled);
            return isEnabled;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to check if element is enabled", e);
            return false;
        }
    }

    /**
     * Check if element is displayed
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if displayed
     */
    public static boolean isElementDisplayed(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Element Displayed: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isDisplayed = element.isDisplayed();
            TestLogger.stepPassed("Element displayed status: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            TestLogger.info("Element is not displayed");
            return false;
        }
    }

    /**
     * Check if element is selected
     * @param locator - Element locator
     * @param waitTimeInSeconds - Time to wait
     * @return true if selected
     */
    public static boolean isElementSelected(By locator, int waitTimeInSeconds) {
        TestLogger.stepStart("Check Element Selected: " + locator);
        try {
            WebDriverWait wait = getExplicitWait();
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isSelected = element.isSelected();
            TestLogger.stepPassed("Element selected status: " + isSelected);
            return isSelected;
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to check if element is selected", e);
            return false;
        }
    }
}


