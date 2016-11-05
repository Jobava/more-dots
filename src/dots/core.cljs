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

(defn hello-world []
  [:div {:name "wrapper"}
   [:div {:id "width-height"}
    [:label {:for "width-id"} "width"]
    [:input {:id "width-id" :type "text" :value "640"}]
    [:label {:for "height-id"} "height"]
    [:input {:id "height-id" :type "text" :value "480"}]]
   [:div {:id "rows-cols"}
    [:label {:for "rows-id"} "rows"]
    [:input {:id "rows-id" :type "text" :value "20"}]
    [:label {:for "cols-id"} "cols"]
    [:input {:id "cols-id" :type "text" :value "20"}]]
   [:div {:id "sep-radius"}
    [:label {:for "sep-id"} "sep"]
    [:input {:id "sep-id" :type "text" :value "10"}]
    [:label {:for "radius-id"} "radius"]
    [:input {:id "radius-id" :type "text" :value "3"}]]
   [:div {:id "button"}
    [:input {:type "button" :value "Update"
             :onClick (fn []
                        #_(js/alert "Hello!")
                        (let [newstate {:width (js/parseInt (js/getElementById "width-id"))
                                        :height (js/parseInt (js/getElementById "height-id"))
                                        :rows (js/parseInt (js/getElementById "rows-id"))
                                        :cols (js/parseInt (js/getElementById "cols-id"))
                                        :sep (js/parseInt (js/getElementById "sep-id"))
                                        :radius (js/parseInt (js/getElementById "radius-id"))}]
                          (!swap app-state assoc :width (:width newstate))
                          (!swap app-state assoc :height (:height newstate))
                          (!swap app-state assoc :rows (:rows newstate))
                          (!swap app-state assoc :cols (:cols newstate))
                          (!swap app-state assoc :sep (:sep newstate))
                          (!swap app-state assoc :radius (:radius newstate))))}]]
   [:svg {:width (:width @app-state)
          :height (:height @app-state)}
    [:g
     (for [x (range (:rows @app-state))
           y (range (:cols @app-state))]
       [:circle
        {:cx (* (:sep @app-state) x)
         :cy (* (:sep @app-state) y)
         :r (:radius @app-state)
              ;;:stroke "black"
         :stroke-width 10}])]]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
