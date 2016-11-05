(ns dots.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

#_(println "This text is printed from src/dots/core.cljs. Go ahead and edit it and see reloading in action.")

(defonce app-state (atom {:width 640
                          :height 480
                          :rows 20
                          :cols 20
                          :sep 10
                          :radius 3}))

(defonce ephemeral-state (atom {:width 640
                                :height 480
                                :rows 20
                                :cols 20
                                :sep 10
                                :radius 3}))

(defn hello-world []
  [:div {:name "wrapper"}
   [:div {:id "width-height"}
    [:label {:for "width-id"} "width"]
    [:input {:id "width-id" :type "text" :value (:width @ephemeral-state)
             :on-change #(swap! ephemeral-state assoc :width (-> % .-target .-value))}]
    [:label {:for "height-id"} "height"]
    [:input {:id "height-id" :type "text" :value (:height @ephemeral-state)
             :on-change #(swap! ephemeral-state assoc :height (-> % .-target .-value))}]]
   [:div {:id "rows-cols"}
    [:label {:for "rows-id"} "rows"]
    [:input {:id "rows-id" :type "text" :value (:rows @ephemeral-state)
             :on-change #(swap! ephemeral-state assoc :rows (-> % .-target .-value))}]
    [:label {:for "cols-id"} "cols"]
    [:input {:id "cols-id" :type "text" :value (:cols @ephemeral-state)
             :on-change #(swap! ephemeral-state assoc :cols (-> % .-target .-value))}]]
   [:div {:id "sep-radius"}
    [:label {:for "sep-id"} "sep"]
    [:input {:id "sep-id" :type "text" :value (:sep @ephemeral-state)
             :on-change #(swap! ephemeral-state assoc :sep (-> % .-target .-value))}]
    [:label {:for "radius-id"} "radius"]
    [:input {:id "radius-id" :type "text" :value (:radius @ephemeral-state)
             :on-change #(swap! ephemeral-state assoc :radius (-> % .-target .-value))}]]
   [:div {:id "button"}
    [:input {:type "button" :value "Update"
             :onClick (fn [] (swap! app-state (fn [] @ephemeral-state)))}]]
   [:svg {:width (:width @app-state)
          :height (:height @app-state)}
    [:g
     (for [x (range (:rows @app-state))
           y (range (:cols @app-state))]
       [:circle
        {:cx (+ (* (:sep @app-state) x) (/ (:sep @app-state) 2))
         :cy (+ (* (:sep @app-state) y) (/ (:sep @app-state) 2))
         :r (:radius @app-state)
              ;;:stroke "black"
         :stroke-width 1}])]]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
